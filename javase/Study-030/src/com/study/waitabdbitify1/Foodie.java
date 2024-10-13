package com.study.waitabdbitify1;

public class Foodie extends Thread {

    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                //判断执行条件
                if (Desk.count == 0) {
                    //当已经吃完十碗,则结束代码
                    break;
                } else {
                    //当还可以继续运行
                    //判断桌子上是否有食物
                    if (Desk.foodFlag == 0) {
                        //如果桌子上没有食物,则等待
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        //如果桌子上有食物,则开始吃
                        Desk.count--;
                        System.out.println("吃完了一碗,现在还能吃" + Desk.count + "碗");
                        //吃完后修改桌子状态并唤醒其他线程
                        Desk.foodFlag = 0;
                        Desk.lock.notifyAll();
                    }
                }
            }
        }
    }
}