package com.study.mpolymorphism;

public class Test {
    public static void main(String[] args) {
        Person p1 = new Student();
        p1.setName("srb");
        p1.setAge(19);
        p1.show();

        Person p2 = new Teacher();
        p2.setName("阿伟老师");
        p2.setAge(30);
        p2.show();

        Person p3 = new Admin();
        p3.setName("管理员");
        p3.setAge(18);
        p3.show();
    }
}
