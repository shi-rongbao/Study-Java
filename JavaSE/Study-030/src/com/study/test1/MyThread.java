package com.study.test1;

public class MyThread extends Thread {
    //创建变量用来模拟电影票的张数
    static int ticket = 1000;

    @Override
    public void run() {
        while (true) {
            try {
                MyThread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (MyThread.class) {
                if (ticket == 0) {
                    break;
                } else {
                    ticket--;
                    System.out.println(getName() + "卖了一张票,现在海参下" + ticket + "张票");
                }
            }
        }
    }
}
