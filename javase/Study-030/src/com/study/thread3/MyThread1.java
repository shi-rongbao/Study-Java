package com.study.thread3;

public class MyThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " @ " + i);
        }
    }
}
