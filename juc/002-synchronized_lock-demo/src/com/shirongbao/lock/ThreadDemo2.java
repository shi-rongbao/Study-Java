package com.shirongbao.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShiRongbao
 * @create 2024/10/9
 * @description:
 */
public class ThreadDemo2 {

    public static void main(String[] args) {
        Shared shared = new Shared();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shared.incr();
            }
        }, "Thread1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shared.decr();
            }
        }, "Thread2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shared.incr();
            }
        }, "Thread3").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shared.decr();
            }
        }, "Thread4").start();

    }

}

class Shared {

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    private int number = 0;

    public void incr() {
        lock.lock();
        try {
            // 判断
            while (number != 0) {
                condition.await();
            }

            // 操作
            number++;
            System.out.println(Thread.currentThread().getName() + " :: " + number);

            // 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decr() {
        lock.lock();
        try {
            // 判断
            while (number != 1) {
                condition.await();
            }
            // 操作
            number--;
            System.out.println(Thread.currentThread().getName() + " :: " + number);

            // 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
