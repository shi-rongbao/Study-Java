package com.study.thread;

public class ThreadDemo1 {
    public static void main(String[] args) {

        /*
            第一种多线程创建方法:
                自定义一个类,继承Thread类,并且重写run方法
                创建自定义类的对象,调用start()方法
         */
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("Thread1");
        t2.setName("Thread2");

        t1.start();
        t2.start();
    }
}
