package com.study.test;

import com.study.util.StudentUtil;
import com.study.util.Student;

import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        Student stu1 = new Student("张三", 19, "男");
        Student stu2 = new Student("李四", 20, "男");
        Student stu3 = new Student("王五", 21, "男");
        list.add(stu1);
        list.add(stu2);
        list.add(stu3);
        System.out.println(StudentUtil.getMaxAgeOfStudent(list));
    }
}
