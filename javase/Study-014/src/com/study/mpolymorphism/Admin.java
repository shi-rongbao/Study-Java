package com.study.mpolymorphism;

public class Admin extends Person {
    @Override
    public void show() {
        System.out.println("管理员的信息为:" + getName() + getAge());
    }
}
