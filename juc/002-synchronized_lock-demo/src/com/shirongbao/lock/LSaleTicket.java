package com.shirongbao.lock;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * @author ShiRongbao
 * @create 2024/10/9
 * @description:
 */
public class LSaleTicket {


    public static void main(String[] args) {
        LTicket lTicket = new LTicket();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                lTicket.saleTicket();
            }
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread1").start();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                lTicket.saleTicket();
            }
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread2").start();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                lTicket.saleTicket();
            }
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread3").start();

    }
}

class LTicket {

    private final ReentrantLock lock = new ReentrantLock();

    private int number = 30;

    public void saleTicket() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了一张票, 还剩" + (--number) + "张票");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
