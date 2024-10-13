package com.study.thread2;

public class ThreadDemo2 {
    public static void main(String[] args) {
        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(mr,"fuck");
        Thread t2 = new Thread(mr,"you");

        t1.setPriority(1);
        t2.setPriority(10);

        t1.start();
        t2.start();
    }
}
