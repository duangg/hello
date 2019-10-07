package com.nolan.ecdsa;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EcdsaTest {
    public static void main(String[] args) throws Exception {
        String plainText = "Hello, world";
        // gen key pair
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        KeyPairGenerator g = KeyPairGenerator.getInstance("EC");
        g.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = g.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // sign
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(plainText.getBytes("UTF-8"));
        byte[] signature = ecdsaSign.sign();
        String pub = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String sig = Base64.getEncoder().encodeToString(signature);
        System.out.printf("pub:%s, sig:%s\n", pub, sig);

        // verify
        Signature ecdsaClientSign = Signature.getInstance("SHA256withECDSA");
        KeyFactory kf = KeyFactory.getInstance("EC");
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(pub));
        PublicKey publicKeyClient = kf.generatePublic(publicKeySpec);
        ecdsaClientSign.initVerify(publicKeyClient);
        ecdsaClientSign.update(plainText.getBytes("UTF-8"));
        boolean result = ecdsaClientSign.verify(Base64.getDecoder().decode(sig));
        System.out.printf("Verify result: %b\n", result);

    }

}
