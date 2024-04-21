package com.study.function;

import com.study.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionDemo4 {
    public static void main(String[] args) {
        /*
            练习:
                集合里存储姓名和年龄,比如:张无忌,15
            要求:
                将数据封装成Student对象并收集到list集合中
         */
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌,15", "周芷若,14", "赵敏,13", "张强,20", "张三丰,100", "张翠三,40");
        //使用匿名内部类完成,将String类型数据转变为Student类型
        List<Student> students = list.stream().map(new Function<String, Student>() {
            @Override
            public Student apply(String s) {
                return new Student(s.split(",")[0], Integer.parseInt(s.split(",")[1]));
            }
        }).collect(Collectors.toList());

        System.out.println(students);
        //使用方法引用完成  需要创建新的构造方法,要求该构造方法的参数与需要和抽象方法的形参保持一致
        List<Student> newList = list.stream().map(Student::new).toList();
        System.out.println(newList);
    }
}
