package com.study.generics2;

public class HaShiQi extends Dog{
    public HaShiQi() {
    }

    public HaShiQi(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("一只叫" + getName() + "的," + getAge() + "岁的哈士奇,正在吃骨头,边吃边拆家");

    }
}
