package com.study.regex;

public class RegexDemo10 {
    public static void main(String[] args) {
        String str1 = "我要学学学学学学编编编编编编编程程程程程程";
        String str2 = "我要学编程";

        String regex = "(.)\\1+";
        System.out.println(str1.replaceAll(regex, "$1"));
    }
}
