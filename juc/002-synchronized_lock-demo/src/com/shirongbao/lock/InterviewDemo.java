package com.shirongbao.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShiRongbao
 * @create 2024/10/9
 * @description:
 */
public class InterviewDemo {

    public static void main(String[] args) {
        Print print = new Print();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    print.printA(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread1").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                try {
                    print.printB(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread2").start();
    }
}

class Print {

    // 设置信号
    private int flag = 0;

    private final Lock lock = new ReentrantLock();

    private final Condition c1 = lock.newCondition();
    private final Condition c2 = lock.newCondition();

    public void printA(int loop) throws InterruptedException {
        lock.lock();

        try {
            // 判断
            while (flag != 0) {
                c1.await();
            }
            // 操作
            System.out.println("A : " + loop);

            // 通知
            flag = 1;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB(int loop) throws InterruptedException {
        lock.lock();

        try {
            // 判断
            while (flag != 1) {
                c2.await();
            }

            // 操作
            System.out.println("B : " + loop);

            // 通知
            flag = 0;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}
