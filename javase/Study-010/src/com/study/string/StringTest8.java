package com.study.string;

public class StringTest8 {
    public static void main(String[] args) {
        String number = "341302200410197219";

        System.out.println("人物信息为:");
        String year = number.substring(6, 10);
        String month = number.substring(10, 12);
        String day = number.substring(12, 14);
        System.out.println("出生年月日:" + year + "年" + month + "月" + day + "日");
        char gender = number.charAt(16);
        int gen = gender - 48;
        if (gen % 10 == 0) {
            System.out.println("性别为女");
        }else System.out.println("性别为男");

    }
}
