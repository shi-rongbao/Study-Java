package com.study.array;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTestStudent2 {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个学生的姓名和年龄:");
        Student stu1 = new Student(sc.next(), sc.nextInt());
        System.out.println("请输入第2个学生的姓名和年龄:");
        Student stu2 = new Student(sc.next(), sc.nextInt());
        list.add(stu1);
        list.add(stu2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + " " + list.get(i).getAge());
        }
    }
}
