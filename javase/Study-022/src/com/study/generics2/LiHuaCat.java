package com.study.generics2;

public class LiHuaCat extends Cat{
    public LiHuaCat() {
    }

    public LiHuaCat(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("一只叫" + getName() + "的," + getAge() + "岁的狸花猫,正在吃鱼");

    }
}
