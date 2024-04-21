package com.study.minterface2;

public class BasketballTeacher extends Person{

    public BasketballTeacher() {
    }

    public BasketballTeacher(String name, int age) {
        super(name, age);
    }

    @Override
    public void play() {
        System.out.println("教打篮球");
    }
}
