package com.study.generics2;

public class BoSiMao extends Cat {
    public BoSiMao() {
    }

    public BoSiMao(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("一只叫" + getName() + "的," + getAge() + "岁的波斯猫,正在吃小饼干");
    }
}
