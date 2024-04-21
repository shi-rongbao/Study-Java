package com.study.test;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        /*
            需求:
                将字符串形式的数据转成整数
                字符串只能是数字,不能有其他字符
                最少一位,最多十位
                0不能开头
         */
        String regex = "[1-9]\\d{0,9}";
        Scanner sc = new Scanner(System.in);
            System.out.println("请输入一串数字:");
            String str = sc.nextLine();
            if (!str.matches(regex)){
                System.out.println("你输入的字符不符合要求!");
            }else {
                int number = 0;
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    int i1 = c - '0';
                    number = number * 10 + i1;
                    System.out.println(i1);
                }
                System.out.println(number);
            }
    }
}
