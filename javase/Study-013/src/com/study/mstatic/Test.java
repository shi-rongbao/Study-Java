package com.study.mstatic;

public class Test {
    public static void main(String[] args) {
        StaticDemo staticDemo1 = new StaticDemo("张三", 19, '男');
        StaticDemo staticDemo2 = new StaticDemo("李四", 20, '男');
        staticDemo1.study();
        staticDemo1.show();
        staticDemo2.study();
        staticDemo2.show();

    }
}
