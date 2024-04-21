package com.study.fordemo;

public class Test1 {
    public static void main(String[] args) {
        /*
            需求: 输出1-5
            需求: 输出5-1
         */
        for (int i = 0; i < 5; i--) {
            System.out.print(i + 1);
        }
        System.out.println();
        System.out.println("===========");
        for (int i = 5; i >0 ; i--) {
            System.out.print(i);
        }
    }
}
