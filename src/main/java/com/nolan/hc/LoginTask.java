package com.nolan.hc;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class LoginTask implements Runnable {
    CloseableHttpClient client;
    BlockingQueue<Integer> idQueue;

    public LoginTask(CloseableHttpClient client, BlockingQueue queue) {
        this.client = client;
        this.idQueue = queue;
    }

    @Override
    public void run() {
        Integer id = 0;
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s start working\n", name);

        try{
            while ((id = idQueue.take()) > 0) {
                System.out.printf("Receive id %d \n", id);
                HttpPost post = new HttpPost("http://192.168.254.71:10100/foundation/passport/login");
                HttpEntity postEntity = new StringEntity("{" +
                        "\"phone\": \"666" + id.toString() + "\"," +
                        "\"password\": \"123456..aa\"," +
                        "\"type\": 1," +
                        "\"platform\": 1," +
                        "\"ttl\": 8640000," +
                        "\"app_key\": \"mall\"" +
                        "}", ContentType.APPLICATION_JSON);
                post.setEntity(postEntity);
                CloseableHttpResponse response = null;
                try {
                    response = client.execute(post);
                    System.out.printf("Result: %s\n", EntityUtils.toString(response.getEntity()));
                } catch (Exception e) {
                    System.out.printf("Thread %s, id %d, execute faild, error: %s\n", name, id, e.getMessage());
                } finally {
                    if (response !=null) {
                        try {
                            response.close();
                        } catch (IOException e) {
                            System.out.printf("Close response failed");
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Get id failed ");
        }

        System.out.printf("Thread %s finished, last id is %d\n", name, id);
    }
}
