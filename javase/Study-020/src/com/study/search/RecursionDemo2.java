package com.study.search;

public class RecursionDemo2 {
    public static void main(String[] args) {
        /*
           用递归求10的阶乘
            1! = 1 * 1!;
            2! = 2 * 1!;
            3! = 3 * 2!
            4! = 4 * 3!
            5! = 5 * 4!
         */
        System.out.println(getNumber(0));
    }

    public static int getNumber(int number) {
        if (number == 1 || number == 0) {
            return 1;
        }
        return number * getNumber(number - 1);
    }
}
