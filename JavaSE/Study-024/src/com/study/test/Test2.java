package com.study.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test2 {
    public static void main(String[] args) {
        //班级里有N个学生
        //要求:
        //70%的概率随机到男生
        //30%的概率随机到女生

        ArrayList<Integer> list = new ArrayList<>();//创建存取数字的集合
        Collections.addAll(list, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0);//集合中存入七个1和三个3,抽到1代表随机男生,抽到0代表随机女生
        Collections.shuffle(list);//打乱集合中元素的顺序
        System.out.println(list);//打印数字集合
        Random r = new Random();//创建随机数random对象r
        System.out.println(list.get(0));//获取到打乱后集合中的第一个元素
        ArrayList<String> studentBoy = new ArrayList<>();//创建存放男生的集合
        Collections.addAll(studentBoy, "srb", "kqw", "ypc", "xwmr");//添加学生元素
        ArrayList<String> studentGirl = new ArrayList<>();//创建存放女生的集合
        Collections.addAll(studentGirl, "zej", "qkx", "yjw", "yqq", "lxr", "lrj");//添加学生元素
        if (list.get(0) == 1) {//如果抽到1,在男生中随机
            int index = r.nextInt(studentBoy.size());//获得随机索引
            System.out.println(studentBoy.get(index));//获得男生
        } else {//如果不是抽到1,则代表抽到0,在女生中随机
            int index = r.nextInt(studentGirl.size());//获得随机索引
            System.out.println(studentGirl.get(index));//获得女生
        }
    }
}
