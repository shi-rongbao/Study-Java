package com.study.thread2;

public class ThreadDemo1 {
    public static void main(String[] args) throws InterruptedException {
        /*MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();*/

        /*MyThread t3 = new MyThread("fuck");
        t3.start();*/

        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        System.out.println(10000);
        Thread.sleep(1000);
        System.out.println(20000);
    }
}
