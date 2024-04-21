package com.study.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Test6 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Study-028\\object3.txt"));

        ArrayList<Student> list  =(ArrayList<Student>)ois.readObject();
        System.out.println(list);
        ois.close();
    }
}
