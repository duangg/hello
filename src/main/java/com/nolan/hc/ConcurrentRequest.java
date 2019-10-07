package com.nolan.hc;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcurrentRequest {

    public static void main(String[] args) throws Exception {
        int idMax = 1000000;
        int workerMax = 50;
        int concurrency = 50;
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10000);
        run(idMax, workerMax, concurrency, queue);

    }

    private static void run(int idMax, int workerMax, int concurrency, BlockingQueue<Integer> queue) throws Exception {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(concurrency);
        connectionManager.setMaxTotal(concurrency * 10);

        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connectionManager).build();

        try {
            Thread[] threads = new Thread[workerMax];


            ProducerThread producer = new ProducerThread(queue, idMax, workerMax);
            producer.start();
            for(int c = 0; c < workerMax; c++) {
                Thread consumer = new LoginThread(client, queue);
                consumer.setName("Consumer_" + String.valueOf(c));
                threads[c] = consumer;
                consumer.start();
            }
            // threads join
            for(Thread consumer : threads) {
                consumer.join();
            }
        } finally {
            client.close();
        }
    }
}
