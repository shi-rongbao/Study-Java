package com.study.test;

public class TestLouTi2 {
    public static void main(String[] args) {
        System.out.println(method(20));
    }

    public static int method(int number) {
        if (number == 1) {
            return 1;
        } else if (number == 2) {
            return 2;
        } else if (number == 3) {
            return 4;
        }
        return method(number - 1) + method(number - 2) + method(number - 3);
    }
}
