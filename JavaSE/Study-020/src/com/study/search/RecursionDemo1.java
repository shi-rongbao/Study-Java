package com.study.search;

public class RecursionDemo1 {
    public static void main(String[] args) {
        /*
            需求:
                利用递归求1-100之间的和
                100+99+98+97...+2+1
         */

        System.out.println(getSum(2100000000));
    }

    public static int getSum(int number) {
        if (number == 1) {
            return 1;
        }
        return number + getSum(number - 1);
    }
}
