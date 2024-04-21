package com.study.interclass2;

public class Outer {
    private int a = 30;

    public void show(){
        int a = 20;

        class Inner{
            String name;
            int age;

            public void method1(){
                System.out.println(a);
                System.out.println("这是局部内部类中的method1方法");
            }
        }

    }
}
