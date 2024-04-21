package com.study.thread3;

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread1 mt1 = new MyThread1();
        MyThread2 mt2 = new MyThread2();

        mt1.setName("非守护线程");
        mt2.setName("守护线程");

        mt1.start();
        mt2.start();

        //当非守护线程执行结束后,守护线程会陆续结束执行

    }
}
