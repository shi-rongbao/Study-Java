package com.study.map;

import com.study.Student;

import java.util.TreeMap;

public class TreeMapDemo2 {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan",23);
        Student s2 = new Student("lisi",24);
        Student s3 = new Student("wangwu",24);
        Student s4 = new Student("zhangsan",23);

        TreeMap<Student,String> tm = new TreeMap<>();
        tm.put(s2,"西安");
        tm.put(s1,"上海");
        tm.put(s3,"北京");
        tm.put(s4,"宿州");

        System.out.println(tm);
    }
}
