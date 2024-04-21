package com.study.waitabdbitify1;

public class Cook extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                //当十碗面已经全部吃完,则结束代码
                if (Desk.count == 0) {
                    break;
                } else {
                    //还有面,继续执行
                    //首先判断桌子上是否还有面条
                    if (Desk.foodFlag == 1) {
                        //如果有面条,则等待
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        //如果没有面条,则做一碗面
                        System.out.println("现在做了一碗面,还剩下" + (Desk.count - 1) + "碗面能做");
                        //修改桌子状态并唤醒其他线程
                        Desk.foodFlag = 1;
                        Desk.lock.notifyAll();
                    }
                }
            }
        }
    }
}
