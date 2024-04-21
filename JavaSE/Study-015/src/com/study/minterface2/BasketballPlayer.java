package com.study.minterface2;

public class BasketballPlayer extends Person{

    public BasketballPlayer() {
    }

    public BasketballPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void play() {
        System.out.println("学打篮球");
    }
}
