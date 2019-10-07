package com.nolan.hc;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.List;
import java.util.concurrent.*;

public class PoolingRequest {
    public static void main(String[] args) {
        int concurrency = 10;
        int idMax = 1000;

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1000);

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(concurrency);
        cm.setMaxTotal(concurrency * 10);
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();

        ProducerThread producer = new ProducerThread(queue, idMax, concurrency);
        producer.start();

        ExecutorService executor = Executors.newWorkStealingPool();
        for(int i = 1; i<=idMax; i++) {
            executor.execute(new LoginTask(client, queue));
        }
        try {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            if (!executor.isShutdown()) {
                System.out.println("SHUTDOWN NOW");
                List<Runnable> runningTasks = executor.shutdownNow();
                if (runningTasks.size() > 0) {
                    System.out.printf("%d task are running\n", runningTasks.size());
                }
            }
        }

    }
}
