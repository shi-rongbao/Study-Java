package com.study.mextends;

public class Test {
    public static void main(String[] args) {
        Zi zi = new Zi();
        zi.show();
    }
}

class Fu{
    String name = "fu";
    String hobby = "喝茶";
}
class Zi extends Fu{
    String name = "Zi";
    String game = "LOL";

    public void show(){
        System.out.println(name);
        System.out.println(super.name);
        System.out.println(super.hobby);
        System.out.println(game);
    }
}
