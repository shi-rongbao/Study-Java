package com.study.test3;

public class MyThread extends Thread {

    //定义变量,用于存储1-100之间的数字
    static int number = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (MyThread.class) {
                if (number == 100) {
                    break;
                } else {
                    number++;
                    if (number % 2 == 1) {
                        System.out.println(getName() + " " + number);
                    }
                }
            }
        }
    }
}
