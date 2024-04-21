package com.study.test;

import java.util.ArrayList;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        /*
            需求:
                键盘录入一些1~100之间的整数,并添加到集合中
                直到集合中所有数据和超过200为止
         */

        ArrayList<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int sum;

        while (true) {
            System.out.println("请输入一个1~100之间的整数:");
            int number = Integer.parseInt(sc.nextLine());
            if (number < 1 || number > 100) {
                System.out.println("输入数据有误,请重新输入!");
                continue;
            }
            list.add(number);
            sum = getSum(list);
            System.out.println("现在的数据和为:" + sum);
            if (sum > 200){
                break;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    public static int getSum(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }
}
