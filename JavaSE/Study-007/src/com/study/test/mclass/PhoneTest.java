package com.study.test.mclass;

public class PhoneTest {
    public static void main(String[] args) {
        Phone p = new Phone();
        p.brand = "华为";
        p.price = 6999;
        System.out.println(p.brand);
        System.out.println(p.price);
        p.call();
        p.playGame();

        Phone p1 = new Phone();
        System.out.println(p1.price);
    }
}
