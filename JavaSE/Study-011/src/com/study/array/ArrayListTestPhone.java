package com.study.array;

import java.util.ArrayList;

public class ArrayListTestPhone {
    public static void main(String[] args) {
        /*
            需求:
                定义javabean类:Phone
                Phone属性:品牌,价格.
                main方法中定义一个集合,存入三个手机对象.
                分别为:小米,1000.苹果,8000.锤子,29999.
                定义一个方法,将价格低于3000的手机信息返回.
         */
        ArrayList<Phone> list = new ArrayList<>();
        Phone p1 = new Phone(1000, "小米");
        Phone p2 = new Phone(8000, "苹果");
        Phone p3 = new Phone(2999, "锤子");
        list.add(p1);
        list.add(p2);
        list.add(p3);
        System.out.println(method(list));

    }

    public static ArrayList<Phone> method(ArrayList<Phone> list) {
        ArrayList<Phone> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPrice() < 3000){
                Phone phone = list.get(i);
                list1.add(phone);
            }
        }
        return list1;
    }
}
