package com.study.thread4;

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("fuck");
        t2.setName("you");

        t1.start();
        t2.start();
    }
}
