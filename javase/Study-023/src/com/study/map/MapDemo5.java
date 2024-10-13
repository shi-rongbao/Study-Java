package com.study.map;

import com.study.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo5 {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 23);
        Student s2 = new Student("lisi", 24);
        Student s3 = new Student("wangwu", 25);
        Student s4 = new Student("wangwu", 25);
        HashMap<Student, String> hm = new HashMap<>();
        hm.put(s1, "上海");
        hm.put(s2, "西安");
        hm.put(s3, "宿州");
        hm.put(s4, "北京");

        Set<Map.Entry<Student, String>> entries = hm.entrySet();
        for (Map.Entry<Student, String> entry : entries) {
            Student key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " = " + value);
        }


        System.out.println("==========================");

        Set<Student> studentSet = hm.keySet();
        for (Student key : studentSet) {
            System.out.println(key + " = " + hm.get(key));
        }

        System.out.println("=======================");

        //lambda表达式遍历
        hm.forEach((key, value) -> System.out.println(key + " = " + value));
    }
}
