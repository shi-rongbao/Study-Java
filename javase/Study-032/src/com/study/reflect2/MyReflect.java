package com.study.reflect2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MyReflect {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        System.out.println(int.class);
        System.out.println(String.class);

        Class clazz = Class.forName("com.study.reflect2.Student");

        Constructor constructor1 = clazz.getConstructor();
        Student s = (Student) constructor1.newInstance();

        Constructor[] constructors1 = clazz.getConstructors();
        for (Constructor constructor : constructors1) {
            System.out.println(constructor);
        }

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        Constructor con1 = clazz.getDeclaredConstructor(String.class);
        System.out.println(con1);
    }
}
