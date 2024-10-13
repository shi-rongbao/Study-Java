package com.shirongbao.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShiRongbao
 * @create 2024/10/9
 * @description:
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        Share share = new Share();

        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    share.print5(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "Thread1").start();
            new Thread(() -> {
                try {
                    share.print10(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "Thread2").start();
            new Thread(() -> {
                try {
                    share.print15(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "Thread3").start();
        }

    }
}

class Share {

    // 标志位
    private int flag = 1;

    private final Lock lock = new ReentrantLock();

    private final Condition c1 = lock.newCondition();
    private final Condition c2 = lock.newCondition();
    private final Condition c3 = lock.newCondition();

    public void print5(int loop) throws InterruptedException {
        // 上锁
        lock.lock();

        try {
            // 判断
            while (flag != 1) {
                c1.await();
            }

            // 操作
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " :: 轮数: " + loop);
            }

            // 通知
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        // 上锁
        lock.lock();

        try {
            // 判断
            while (flag != 2) {
                c2.await();
            }

            // 操作
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " :: 轮数: " + loop);
            }

            // 通知
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        // 上锁
        lock.lock();

        try {
            // 判断
            while (flag != 3) {
                c3.await();
            }

            // 操作
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " :: 轮数: " + loop);
            }

            // 通知
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}
