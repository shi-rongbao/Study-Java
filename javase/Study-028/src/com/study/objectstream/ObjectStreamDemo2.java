package com.study.objectstream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectStreamDemo2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Study-028\\object.txt"));

        Student s = (Student) ois.readObject();

        System.out.println(s);
        ois.close();
    }
}
