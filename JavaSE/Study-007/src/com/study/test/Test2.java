package com.study.test;

public class Test2 {
    public static void main(String[] args) {
        getNum();
        extracted();
    }

    private static void extracted() {
        System.out.println("HelloWorld");
        System.out.println("HelloWorld");
        System.out.println("HelloWorld");
        System.out.println("HelloWorld");
    }

    public static void getNum() {
        int count = 0;
        for (int i = 0; i < 700000; i++) {
            boolean flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(i);
                count++;
            }
        }
        System.out.println("共有" + count + "个质数");
    }
}
