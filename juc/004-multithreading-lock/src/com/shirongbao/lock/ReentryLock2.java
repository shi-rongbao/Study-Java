package com.shirongbao.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShiRongbao
 * @create 2024/10/14
 * @description:
 */
public class ReentryLock2 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            try {
                // 上锁
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 外层");
                try {
                    // 上锁
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 内层");
                } finally {
                    // 释放锁
                    // 如果这里不释放锁，下面再想拿到锁，就拿不到了
                    // lock.unlock();
                }
            } finally {
                // 释放锁
                lock.unlock();
            }
        }, "Thread1").start();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("卡住了");
            } finally {
                lock.unlock();
            }
        }, "Thread2").start();
    }
}
