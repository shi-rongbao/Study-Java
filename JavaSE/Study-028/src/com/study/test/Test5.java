package com.study.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Test5 {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Study-028\\object3.txt"));

        Student s1 = new Student("zhangsan", 23, "西安");
        Student s2 = new Student("Lisi", 24, "北京");
        Student s3 = new Student("wangwu", 25, "上海");

        ArrayList<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        oos.writeObject(list);

        oos.close();
    }
}
