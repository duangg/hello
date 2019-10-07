package com.nolan.hc;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class ProducerThread extends Thread {
    BlockingQueue<Integer> queue;
    int maxId;
    int workerCount;
    public ProducerThread(BlockingQueue<Integer> queue, int maxId, int workerCount) {
        this.queue = queue;
        this.maxId = maxId;
        this.workerCount = workerCount;
    }

    @Override
    public void run() {
        // 生产id
        int i = 0;
        try {
            for (i = 1; i <= maxId; i++) {
                queue.put(i);
                System.out.printf("Produce %d \n", i);
            }
        } catch (InterruptedException e) {
            System.out.printf("Producer interrupted, id: %d\n", i);
        }
        // 关闭worker
        try {
            for (int c = 0; c < workerCount; c++) {
                queue.put(-1);
            }
        } catch (InterruptedException e) {
            System.out.printf("Producer interrupted, id: %d\n", i);
        }
        System.out.println("Producer finished" );
    }
}
