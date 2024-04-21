package com.study.array;

import java.util.ArrayList;

public class ArrayListTestStudent {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        Student stu1 = new Student("shirongbao",19);
        Student stu2 = new Student("dashuaige",20);
        list.add(stu1);
        list.add(stu2);
        System.out.println(list);
    }
}
