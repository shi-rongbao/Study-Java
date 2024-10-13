package com.study.map;

import com.study.Student;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapDemo1 {
    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 23);
        Student s2 = new Student("lisi", 24);
        Student s3 = new Student("wangwu", 25);

        HashMap<Student, String> hm = new HashMap<>();
        hm.put(s1, "西安");
        hm.put(s2, "上海");
        hm.put(s3, "纽约");

        //通过键找值遍历集合
        Set<Student> keys = hm.keySet();
        //增强for
        for (Student key : keys) {
            System.out.println(key + " @ " + hm.get(key));
        }
        //lambda表达式
        keys.forEach(key -> System.out.println(key + " @ " + hm.get(key)));
        //迭代器
        Iterator<Student> it = keys.iterator();
        while (it.hasNext()){
            System.out.println(it.next() + " @ " + hm.get(it.next()));
        }

        //通过entry对象遍历
        Set<Map.Entry<Student, String>> entries = hm.entrySet();

        //lambda表达式
        hm.forEach((key, value) -> System.out.println(key + " @ " + value));
    }
}
