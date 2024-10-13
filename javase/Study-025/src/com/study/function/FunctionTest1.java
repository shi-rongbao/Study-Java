package com.study.function;

import com.study.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FunctionTest1 {
    public static void main(String[] args) {
        /*
            练习1:
                集合中存储一些字符串的数据,比如:张三,23
                收集到Student类型的数组当中(使用方法引用完成)
         */

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张三,23", "李四,24", "王五,25", "赵六,26");
        //第一步先将list放入stream流中
        //第二部将String类型的数据通过map方法变为Student类型(通过方法引用调用Student的构造方法,需要自己再写一个形参想通的构造方法)
        //第三部将转换好的Student对象放入Student数组中(通过数组的方法引用Student[]::new)
        Student[] student = list.stream().map(Student::new).toArray(Student[]::new);
        System.out.println(Arrays.toString(student));
    }
}
