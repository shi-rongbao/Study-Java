package com.study.objectstream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectStreamDemo1 {
    public static void main(String[] args) throws IOException {
        Student s = new Student("张三",23);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Study-028\\object.txt"));
        oos.writeObject(s);
        oos.close();
    }
}
