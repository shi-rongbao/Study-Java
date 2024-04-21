package com.study.test2;

interface Inter {
    void show();
}

class Outer implements Inter {

    static Outer method() {
        return new Outer();
    }

    @Override
    public void show() {
        System.out.println("HelloWorld");
    }
}

public class OuterDemo {
    public static void main(String[] args) {
        Outer.method().show();
    }
}
