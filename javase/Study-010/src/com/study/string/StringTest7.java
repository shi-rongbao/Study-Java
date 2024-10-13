package com.study.string;

public class StringTest7 {
    public static void main(String[] args) {
        String phoneNumber = "13299105307";
        String str1 = phoneNumber.substring(3, 7);
        System.out.println(str1);

        String str = "9910";
        //用substring方法截取的字符串也是new出来的
        System.out.println(str == str1);

        String str2 = phoneNumber.substring(7);
        System.out.println(str2);


    }
}
