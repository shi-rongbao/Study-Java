package com.study.reflect1;

public class MyReflect {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz1 = Class.forName("com.study.reflect1.Student");
        System.out.println(clazz1);

        Class clazz2 = Student.class;
        System.out.println(clazz2);

        Student s = new Student();
        Class clazz3 = s.getClass();
        System.out.println(clazz3);
    }
}
