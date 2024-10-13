package com.study.test7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    static ArrayList<Integer> list;

    public MyCallable(ArrayList<Integer> list) {
        this.list = list;
    }



    @Override
    public Integer call() throws Exception {
        ArrayList<Integer> listBox = new ArrayList<>();

        while (true){
            synchronized (MyCallable.class){
                if (list.size() == 0){
                    System.out.print("在此次抽奖过程中,抽奖箱1共产生了" + listBox.size() + "个奖项.\r\n分别为:");
                    int sum = 0;
                    for (int price : listBox) {
                        System.out.print(price + " ");
                        sum += price;
                    }
                    System.out.print("最高奖项为:" + Collections.max(listBox) + "元 ");
                    System.out.println("总计金额为:" + sum + "元 ");
                    break;
                }else {
                    Collections.shuffle(list);
                    int price = list.remove(0);
                    listBox.add(price);
                }
            }
                Thread.sleep(10);
        }
        return Collections.max(listBox);
    }
}
