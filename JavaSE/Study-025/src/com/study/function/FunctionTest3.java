package com.study.function;

import com.study.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FunctionTest3 {
    public static void main(String[] args) {
        /*
            练习3:
                创建集合添加学生对象,学生对象属性:name,age
                把姓名和年龄拼接成:张三-23的字符串,并放到数组当中(使用方法引用完成)
         */

        ArrayList<Student> list = new ArrayList<>();
        Student s1 = new Student("张三", 23);
        Student s2 = new Student("李四", 24);
        Student s3 = new Student("王五", 25);
        Student s4 = new Student("赵六", 26);
        Collections.addAll(list, s1, s2, s3, s4);


        String[] student = list.stream().map(Student::method).toArray(String[]::new);
        System.out.println(Arrays.toString(student));
    }
}
