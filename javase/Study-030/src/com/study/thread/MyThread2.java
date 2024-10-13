package com.study.thread;

public class MyThread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            //返回当前调用者的线程对象
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + "HelloWorld");
        }
    }
}
