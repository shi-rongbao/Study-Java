package com.study.string;

import java.util.Scanner;

public class StringTest10 {
    public static void main(String[] args) {
        /*
            练习:
                键盘录入一个字符串,
                要求1:长度为小鱼等于9
                要求2:只能是数字
                将内容变成罗马数字
                下面是阿拉伯数字跟罗马数字的对比关系:
                1-Ⅰ 2-Ⅱ 3-Ⅲ 4-Ⅳ 5-Ⅴ 6-Ⅵ 7-Ⅶ 8-Ⅷ 9-Ⅸ
                注意点:
                罗马数字没有0
                如果键盘录入的数字包涵0,可以变成""(长度为0的字符串)
         */

        Scanner sc = new Scanner(System.in);
        String inputStr;
        while (true) {
            System.out.println("请输入一个长度小于等于9的数字:");
            inputStr = sc.next();
            if (checkString(inputStr)) {
                break;
            }else{
                System.out.println("输入有误!");
                continue;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);
            int number = c - 48;
            String s = changeRoma(number);
            sb.append(s);
        }
        System.out.println(sb);
    }

    public static String changeRoma(int number){
        String[] arr = {"", "Ⅰ", "Ⅱ", "Ⅲ", "Ⅳ", "Ⅴ", "Ⅵ", "Ⅶ", "Ⅷ", "Ⅸ"};
        return arr[number];
    }
    public static boolean checkString(String str) {
        if (str.length() > 9) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
