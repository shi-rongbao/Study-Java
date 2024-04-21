package com.study.mabstract2;

public class Test {
    public static void main(String[] args) {
        Frog f = new Frog("青蛙",12);
        f.drink();
        f.eat();

        Dog d = new Dog("狗",5);
        d.drink();
        d.eat();

        Sheep s = new Sheep("山羊",8);
        s.drink();
        s.eat();

    }
}
