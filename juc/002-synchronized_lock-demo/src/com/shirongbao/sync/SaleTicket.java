package com.shirongbao.sync;

import static java.lang.Thread.sleep;

/**
 * @author ShiRongbao
 * @create 2024/10/9
 * @description:
 */
public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticket.saleTicket();
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread1").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticket.saleTicket();
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread2").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticket.saleTicket();
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Thread3").start();
    }

}

class Ticket {
    private int number = 30;

    public synchronized void saleTicket() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了一张票, 还剩" + (--number) + "张票");
        }
    }
}
