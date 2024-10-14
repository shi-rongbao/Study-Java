package com.shirongbao.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author ShiRongbao
 * @create 2024/10/13
 * @description:
 */
public class EightLock {

    public static void main(String[] args) throws InterruptedException {

        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone1.sendSMS();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread1 is invoke");
        }, "Thread1").start();

        Thread.sleep(100);

        new Thread(() -> {
            // phone1.sendEmail();
            // phone1.getHello();
            phone2.sendEmail();
            System.out.println("Thread2 is invoke");
        }, "Thread2").start();
    }

}

class Phone {
    public static synchronized void sendSMS() throws InterruptedException {
        // 睡3秒
        TimeUnit.SECONDS.sleep(1);
        System.out.println("------sendSMS");
    }

    public static synchronized void sendEmail () {
        System.out.println("------sendEmail");
    }

    public void getHello () {
        System.out.println("------getHello");
    }
}