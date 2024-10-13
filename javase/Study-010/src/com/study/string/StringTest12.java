package com.study.string;

import java.util.Random;
import java.util.Scanner;

public class StringTest12 {
    public static void main(String[] args) {
        /*
            需求:
                键盘录入任意字符串,打乱里面的内容
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        String str = sc.nextLine();
        System.out.println(method(str));
    }

    public static String method(String str) {
        Random r = new Random();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int number = r.nextInt(str.length());
            char c = chars[i];
            chars[i] = chars[number];
            chars[number] = c;
        }
        return new String(chars);
    }
}
