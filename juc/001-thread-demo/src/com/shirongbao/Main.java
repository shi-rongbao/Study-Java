package com.shirongbao;

import static java.lang.Thread.sleep;

/**
 * @author ShiRongbao
 * @create 2024/10/9
 * @description:
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());

            while (true) {
                System.out.println(1);
            }
        }, "Thread1");

        // 设置线程为守护线程
        thread1.setDaemon(true);
        thread1.start();


        System.out.println(Thread.currentThread().getName() + ": isOver");
        sleep(200);
    }
}
