package com.study.string;

import java.util.Scanner;

public class StringTest3 {
    public static void main(String[] args) {
        /*
            需求:
                键盘录入一个字符串,统计该字符串中大写字母字符,小写字母字符,数字字符出现的个数;
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        String str = sc.next();
        int bijCount = 0;
        int smallCount = 0;
        int numberCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 65 && c <= 90) {
                bijCount++;
            } else if (c >= 97 && c <= 122) {
                smallCount++;
            } else if (c >= '0' && c <= '9') {
                numberCount++;
            }
        }
        System.out.println("大写字母共有" + bijCount + "个");
        System.out.println("小写字母共有" + smallCount + "个");
        System.out.println("数字共有" + numberCount + "个");
    }
}
