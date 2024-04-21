package com.study.method;

public class MethodDemo1 {
    public static void main(String[] args) {
        int result = getSum(10, 30);
        System.out.println(result);
    }

    public static int getSum(int num1, int num2) {
        int sum = num1 + num2;
        return sum;
    }
}
