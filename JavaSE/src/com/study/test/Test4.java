package com.study.test;

public class Test4 {
    public static void main(String[] args) {
        int height1 = 150;
        int height2 = 165;
        int height3 = 210;
        int temp = height1 > height2 ? height1 : height2;
        int max = temp > height3 ? temp : height3;
        System.out.println(max);
    }
}
