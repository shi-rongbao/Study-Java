package com.study.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test1 {
    public static void main(String[] args) {
        //班级里有N个学生,实现随机点名器
        ArrayList<String> student = new ArrayList<>();
        Collections.addAll(student, "srb", "kqw", "ypc", "zej", "qkx", "dsb");
//        Random r = new Random();
//        for (int i = 0; i < 50; i++) {
//            int index = r.nextInt(student.size());
//            System.out.println("这次点名点到的人是:" + student.get(index));
//        }

        System.out.println("========================");

        Collections.shuffle(student);
        String name = student.get(0);
        System.out.println("这次点名点到的人是:" + name);

    }
}
