package com.shirongbao.sync;

/**
 * @author ShiRongbao
 * @create 2024/10/9
 * @description:
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        Shared shared = new Shared();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shared.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shared.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shared.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread3").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shared.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread4").start();

    }

}

class Shared {

    private int number = 0;

    public synchronized void incr() throws InterruptedException {
        // 判断
        // 如果wait在if里面，可能存在虚假唤醒
        while (number != 0) {
            wait(); // 在哪里睡，就在哪里醒来
        }
        // 操作
        number ++;
        System.out.println(Thread.currentThread().getName() + " :: " + number);

        // 通知
        notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        // 判断
        while (number != 1) {
            wait();
        }

        // 操作
        number --;
        System.out.println(Thread.currentThread().getName() + " :: " + number);

        // 通知
        notifyAll();
    }
}
