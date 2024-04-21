package com.study.test2;

/**
 * @author ShiRongbao
 * @create 2024/3/18 22:13
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        // 创建B的对象b 因为B的构造方法中要传入一个接口InterB 这里使用匿名内部类，new InterB() {  在这里重写showB方法就可以了    };  这个方法返回一个B对象
        B b = new B(new InterB() {
            @Override
            public void showB() {
                System.out.println("这是showB方法");
            }
        });
        // 使用b对象就能调用methodB()方法了
        b.methodB();
    }
}

interface InterB {
    void showB();
}

class B {
    InterB b;

    public B(InterB b) {
        this.b = b;
    }

    public void methodB() {
        b.showB();
    }
}


