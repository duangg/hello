package com.nolan.concurrent;

import java.time.Instant;
import java.util.concurrent.*;

public class Scheduled {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Long ts = Instant.now().getEpochSecond();
            System.out.println(ts);
        };
//        executor.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(task, 0, 2, TimeUnit.SECONDS);
    }
}
