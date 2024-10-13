package com.study.string;

import java.util.Scanner;

public class StringTest6 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int money;
        while (true) {
            System.out.println("请输入一个金额:");
            money = sc.nextInt();
            if (money >= 0 && money <= 9999999) {
                break;
            } else {
                System.out.println("您输入的金额有问题!");
            }
        }

        String result = "";

        while (true) {
            int ge = money % 10;
            String capitalNumber = getCapitalNumber(ge);
            result = capitalNumber + result;

            money = money / 10;
            if (money == 0) {
                break;
            }
        }

        int count = 7 - result.length();
        for (int i = 0; i < count; i++) {
            result = "零" + result;
        }

        String[] arr = {"百", "十", "万", "千", "百", "十", "元"};
        String moneyStr = "";
        for (int i = 0; i < result.length(); i++) {
            char c = result.charAt(i);
            moneyStr = moneyStr + c + arr[i];
        }
        System.out.println(moneyStr);
    }

    public static String getCapitalNumber(int number) {
        String[] chs = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        return chs[number];
    }
}