package com.study.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionDemo2 {
    public static void main(String[] args) {
        Collection<Student> coll = new ArrayList<>();
        Student s1 = new Student("srb",19);
        Student s2 = new Student("kqw",18);
        Student s3 = new Student("ypc",20);
        Student s4 = new Student("srb",19);

        coll.add(s1);
        coll.add(s2);
        coll.add(s3);

        System.out.println(coll.contains(s4));
    }
}
