package com.study.map;

import com.study.Student;

import java.util.TreeMap;

public class TreeMapDemo4 {
    public static void main(String[] args) {
        TreeMap<Student,String> tm = new TreeMap<>();

        Student s1 = new Student("zhangsan",23);
        Student s2 = new Student("lisi",24);
        Student s3 = new Student("wangwu",24);
        Student s4 = new Student("zhaoliu",26);

        tm.put(s1,"xian");
        tm.put(s2,"beijing");
        tm.put(s3,"shanghai");
        tm.put(s4,"shenzhen");

        System.out.println(tm);
    }
}
