package com.study.thread5;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        //此线程为main线程
        MyThread t = new MyThread();
        t.setName("fuck");

        t.start();

        //join方法,指将t线程插入到当前线程之前,(当前线程为main线程)
        t.join();
        //当t线程内容全部执行完毕,才回执行当前线程
        //此段代码执行在main线程中
        for (int i = 0; i < 10; i++) {
            System.out.println("main" + i);
        }
    }
}
