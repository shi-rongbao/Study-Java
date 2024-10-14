package com.shirongbao.lock;

/**
 * @author ShiRongbao
 * @create 2024/10/14
 * @description:
 */
public class ReentryLock1 {
    public static void main(String[] args) {
        new ReentryLock1().add();
        Object o = new Object();

        new Thread(() -> {
            // 锁的是同一个对象o
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "::外层");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + "::中层");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + "::内层");
                    }
                }
            }
        }, "Thread1").start();
    }

    public synchronized void add () {
        add();
    }
}
