package com.study.minterface;

public class Test {
    public static void main(String[] args) {
        Dog d = new Dog("修勾", 13);
        System.out.println(d.getName() + d.getAge());
        d.eat();
        d.swim();
    }
}
