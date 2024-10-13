package com.study.waitandnotify2;

import java.util.concurrent.ArrayBlockingQueue;

public class Foodie extends Thread {
    ArrayBlockingQueue<String> queue;

    public Foodie(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String take = queue.take();
                System.out.println("拿" + take);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
