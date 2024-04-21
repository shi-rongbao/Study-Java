package com.study.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test3 {
    public static void main(String[] args) {
        /*
            班级里有N个学生
            要求:
                被点到名字的同学不会被再点到。
                但是如果班级中所有的学生都点完了,需要重新开启第二轮点名.
         */

        ArrayList<String> student = new ArrayList<>();
        Collections.addAll(student, "srb", "kqw", "ypc", "zej", "qkx", "yjw", "yqq", "lxr", "lrj");
        ArrayList<String> newStudent = new ArrayList<>();
        Random r = new Random();
        for (int i = 1; i <= 10; i++) {
            System.out.println("第" + i + "轮点名");
            while (student.size() != 0) {
                int index = r.nextInt(student.size());
                String name = student.remove(index);
                newStudent.add(name);
                System.out.println(name);
            }
            student.addAll(newStudent);
            newStudent.clear();
        }
    }
}
