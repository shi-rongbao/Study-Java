package com.study.fordemo;

import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        /*
            需求:键盘录入两个数字,表示一个范围
            统计这个范围中
            既能被3整除,又能被5整除的数字有多少个
         */

        int count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个数字:");
        int start = sc.nextInt();
        System.out.println("请输入第二个数字:");
        int end = sc.nextInt();
        for (int i = start; i <= end; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                count++;
                System.out.println(i);
            }
        }
        System.out.println("这样的数字共有:" + count + "个");
    }
}
