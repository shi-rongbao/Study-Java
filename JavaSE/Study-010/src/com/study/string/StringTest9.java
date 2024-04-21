package com.study.string;

public class StringTest9 {
    public static void main(String[] args) {
        String str = "我是你爹,FuckYou, suck my penis";
        String[] arr = {"Fuck", "dick", "tmd", "penis"};
        for (int i = 0; i < arr.length; i++) {
            str = str.replace(arr[i], "love");
        }
        System.out.println(str);
    }
}
