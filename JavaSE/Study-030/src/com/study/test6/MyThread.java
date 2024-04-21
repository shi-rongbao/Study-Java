package com.study.test6;

import java.util.ArrayList;
import java.util.Collections;

public class MyThread extends Thread {
    ArrayList<Integer> list;

    public MyThread(ArrayList<Integer> list) {
        this.list = list;
    }


    @Override
    public void run() {
        //每个线程都有一个栈内存
        ArrayList<Integer> listBox = new ArrayList<>();
        while (true) {
            synchronized (MyThread.class) {
                if (list.size() == 0) {
                    //当抽奖结束,打印奖箱
                    System.out.print("在此次抽奖过程中,抽奖箱1共产生了" + listBox.size() + "个奖项.\r\n分别为:");
                    int sum = 0;
                    for (int price : listBox) {
                        System.out.print(price + " ");
                        sum += price;
                    }
                    System.out.print("最高奖项为:" + Collections.max(listBox) + "元 ");
                    System.out.println("总计金额为:" + sum + "元 ");
                    break;
                } else {
                    Collections.shuffle(list);
                    int price = list.remove(0);
                    listBox.add(price);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
