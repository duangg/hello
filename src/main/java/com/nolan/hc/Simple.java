package com.nolan.hc;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Simple {
    public static void main(String[] args) throws IOException {
        Simple s = new Simple();
//        System.out.println(s.getBd("天气"));
        System.out.println(s.post());
    }

    public String getBd(String s) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://www.baidu.com");
        String res = "";
        try {
            CloseableHttpResponse response = client.execute(get);
            res = EntityUtils.toString(response.getEntity());
        } finally {
            client.close();
        }
        return res;
    }

    public String post() throws IOException {
        String res = "";
        StringEntity entity = new StringEntity("{\n" +
                "  \"phone\": \"13818178025\",\n" +
                "  \"password\": \"poiuyt\",\n" +
                "  \"type\": 1,\n" +
                "  \"platform\": 1,\n" +
                "  \"ttl\": 8640000,\n" +
                "  \"app_key\": \"mall\"\n" +
                "}", ContentType.create("application/json"));
        HttpPost post = new HttpPost("http://192.168.254.71:10100/foundation/passport/login");
        post.setEntity(entity);
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(post);
        try {
            HttpEntity resEntity = response.getEntity();
            res = EntityUtils.toString(resEntity);
        } finally {
            client.close();
        }
        return res;
    }

}
