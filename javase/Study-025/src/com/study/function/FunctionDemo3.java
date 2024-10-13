package com.study.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

public class FunctionDemo3 {
    public static void main(String[] args) {
        /*
            方法引用(引用成员方法)
            格式
                其他类:其他类对象::方法名
                本类:this::方法名
                父类:super::方法名
            需求:
                集合中有一些名字,按照要求过滤数据
                数据:"张无忌","周芷若","赵敏","张强","张三丰"
                要求:只要以张开头,而且名字是三个字的
         */

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰");
        list.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("张");
            }
        }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() == 3;
            }
        }).forEach(System.out :: println);

        //创建StringOperation的对象
        StringOperation so = new StringOperation();
        list.stream().filter(so:: method).forEach(System.out :: println);

        //注意:static静态方法中没有this关键字,如果再静态方法中引用本类方法,必须创建本类的对象,用对象来方法引用
        //list.stream().filter(this:: method).forEach(System.out :: println);

    }
    public boolean method(String s){
        return s.startsWith("张") && s.length() == 3;
    }
}
