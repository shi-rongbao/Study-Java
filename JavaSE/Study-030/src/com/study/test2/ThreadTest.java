package com.study.test2;

public class ThreadTest {
    public static void main(String[] args) {
        /*
            有100份礼品,两人同时发送,当剩下的礼品小于10份的时候则不在送出.
            利用多线程模拟该过程并将线程的名字和礼物的剩余数量打印出来.
         */

        MyThread t = new MyThread();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.setName("人1");
        t2.setName("人2");

        t1.start();
        t2.start();
    }
}
