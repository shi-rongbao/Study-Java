package com.study.thread;

public class ThreadDemo2 {
    public static void main(String[] args) {
        /*
            多线程第二种启动方式
                1.自定义一个类,实现Runnable接口
                2.重写run方法
                3.创建自定义类的对象
                4.创建Thread类的对象
                5.将自定义对象传给Thread对象并开启多线程
         */

        //创建自定义类对象
        MyThread2 mt = new MyThread2();

        //创建两个线程对象,并将任务mt传递给对象
        Thread t1 = new Thread(mt);
        Thread t2 = new Thread(mt);

        //设置线程的名字
        t1.setName("Thread1");
        t2.setName("Thread2");

        //启动线程
        t1.start();
        t2.start();
    }
}
