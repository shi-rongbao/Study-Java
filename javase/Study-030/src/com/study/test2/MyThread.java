package com.study.test2;

public class MyThread implements Runnable {
    //创建变量,用于记录礼物的数量
    int count = 100;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (MyThread.class) {
                if (count < 10) {
                    System.out.println("礼物数量不足,停止送礼");
                    break;
                } else {
                    count--;
                    System.out.println(Thread.currentThread().getName() + "送出了一份礼物,礼物还剩下" + count + "份");
                }
            }
        }
    }
}
