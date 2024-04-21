package com.study.safe2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {
    static int ticket = 0;
    static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                lock.lock();
                if (ticket == 100) {
                    break;
                } else {
                    ticket++;
                    System.out.println(getName() + "在卖第" + ticket + "张票");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
