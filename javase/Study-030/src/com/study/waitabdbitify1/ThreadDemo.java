package com.study.waitabdbitify1;

public class ThreadDemo {
    public static void main(String[] args) {
        //创建吃货和厨师两个线程
        Cook cook = new Cook();
        Foodie foodie = new Foodie();

        cook.start();
        foodie.start();
    }
}
