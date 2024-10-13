package com.study.regex;

public class RegexDemo2 {
    public static void main(String[] args) {
        /*
            需求:
                请编写正则表达式验证用户输入的手机号码是否满足要求
                请编写正则表达式验证用户输入的邮箱号是否满足要求
                请编写正则表达式验证用户输入的座机号码号码是否满足要求
         */

        String regex1 = "1[3-9]\\d{9}";
        String regex2 = "0\\d{2,3}-?[1-9]\\d{4,9}";
        String regex3 = "\\w+@[\\w&&[^_]]{2,6}(\\.[a-zA-Z]{2,3}){1,2}";
        System.out.println("25022903@qq.com.cn".matches(regex3));

        String regex4 = "";
    }
}
