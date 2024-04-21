package com.study.generics;

import java.util.ArrayList;

public class GenericsDemo3 {
    public static void main(String[] args) {
        //定义一个方法,形参是一个集合,但是集合中的数据类型不确定
        /*
            通配符:
                ? : 也表示不确定的类型
                ? extends E : 表示可以传递E或者E所有的子类类型
                ? super E : 表示可以传递E或者E所有的父类类型
         */


        ArrayList<Ye> list1 = new ArrayList<>();
        ArrayList<Fu> list2 = new ArrayList<>();
        ArrayList<Zi> list3 = new ArrayList<>();
        ArrayList<Student> list4 = new ArrayList<>();

        method(list1);
        method(list2);
        method(list3);
        //method(list4);  Student 类不是 Ye 类的子类类型
    }

    public static void method(ArrayList<? super Zi> list) {

    }
}
class Ye{}
class Fu extends Ye{}
class Zi extends Fu{}

class Student{}
