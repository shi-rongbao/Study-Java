package com.study.function;

import com.study.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FunctionTest2 {
    public static void main(String[] args) {
        /*
            练习2:
                创建集合添加学生对象,学生对象属性:name,age
                只获取姓名并放到数组当中
         */
        ArrayList<Student> list = new ArrayList<>();
        Student s1 = new Student("张三", 23);
        Student s2 = new Student("李四", 24);
        Student s3 = new Student("王五", 25);
        Student s4 = new Student("赵六", 26);
        Collections.addAll(list, s1, s2, s3, s4);

        //通过方法引用
        //先将list放入stream流中
        //再通过Student类中的getName方法获取到Student对象的name值
        //再将name值通过方法引用的方法放入数组中
        String[] name = list.stream().map(Student::getName).toArray(String[]::new);
        System.out.println(Arrays.toString(name));
    }
}
