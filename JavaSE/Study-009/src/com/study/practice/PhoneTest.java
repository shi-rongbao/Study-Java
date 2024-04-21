package com.study.practice;

public class PhoneTest {
    public static void main(String[] args) {
        Phone[] arr = new Phone[3];

        Phone p1 = new Phone("华为", 7999, "陶瓷白");
        Phone p2 = new Phone("小米", 1999, "屌丝黑");
        Phone p3 = new Phone("苹果", 8999, "远峰蓝");

        arr[0] = p1;
        arr[1] = p2;
        arr[2] = p3;

        double result = getAve(p1.getPrice(), p2.getPrice(), p3.getPrice());

        System.out.println(result);
    }

    public static double getAve(int price1, int price2, int price3) {
        int sum = price1 + price2 + price3;
        double ave = sum / 3;
        return ave;
    }
}
