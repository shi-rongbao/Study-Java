package com.study.test3;

public class ThreadDemo {
    public static void main(String[] args) {
        /*
            同时开启两个线程,共同获取1-100之间的所有数字
            要求:将输出所有的奇数
         */
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}
