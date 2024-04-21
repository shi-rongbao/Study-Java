package com.study.treeset;

import com.study.Student1;

import java.util.TreeSet;

public class TreeSetDemo4 {
    public static void main(String[] args) {
        TreeSet<Student1> ts = new TreeSet<>((o1, o2) -> {
            int i = o2.getScore() - o1.getScore();
            i = i == 0 ? o2.getChinese() - o1.getChinese() : i;
            i = i == 0 ? o2.getMath() - o1.getMath() : i;
            i = i == 0 ? o2.getEnglish() - o1.getEnglish() : i;
            i = i == 0 ? o2.getAge() - o1.getAge() : i;
            i = i == 0 ? o2.getName().compareTo(o1.getName()) : i;
            return i;
        });

        Student1 s1 = new Student1(532,132,149,120,"zhangsan",23);
        Student1 s2 = new Student1(572,139,148,120,"zhangsan",23);
        Student1 s3 = new Student1(532,129,149,120,"zhangsan",23);
        Student1 s4 = new Student1(532,132,150,120,"zhangsan",23);
        Student1 s5 = new Student1(532,132,149,120,"zhangsan",23);

        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);
        ts.add(s5);

        for (Student1 t : ts) {
            System.out.println(t);
        }
    }
}
