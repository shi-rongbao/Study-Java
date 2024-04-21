package com.study.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest3 {
    public static void main(String[] args) {
        /*
            现在又两个ArrayList集合,
            第一个集合中:存储6名男演员的名字和年龄.第二个集合中:存储6名女演员的名字和年龄.
            姓名和年龄中间用逗号隔开.比如:张三,23
            要求完成如下的操作:
            1,男演员只要名字为3个字的前两人
            2,女演员只要姓杨的,并且不要第一个
            3,把过滤后的男演员姓名和女演员姓名合并到一起
            4,将上一步的演员信息封装成Actor对象.
            5,将所有的演员对象都保存到List集合中.
         */

        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "孙博,20", "时荣宝,19", "林小煜,19", "马贤哲,21", "陈杰,19", "牛佳慧,20");
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "刘杨梅,20", "齐珂欣,19", "杨紫琴,19", "杨孙吧,21", "陈绍辉,19", "小女孩,20");
        Stream<String> stream1 = list1.stream()
                .filter(name -> name.split(",")[0].length() == 3)
                .limit(2);
        Stream<String> stream2 = list2.stream()
                .filter(name -> name.split(",")[0].startsWith("杨"))
                .skip(1);
        //调用Stream类中的静态方法concat方法,将两个流合并为一个流
        Stream<String> stream = Stream.concat(stream1, stream2);

        //将stream流中的数据封装成Actor对象
        List<Actor> list = stream.map(s -> new Actor(s.split(",")[0], Integer.parseInt(s.split(",")[1]))).collect(Collectors.toList());
        System.out.println(list);
    }
}
