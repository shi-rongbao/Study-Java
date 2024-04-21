package com.study.immutable;

import java.util.Map;
import java.util.Set;

public class ImmutableDemo3 {
    public static void main(String[] args) {
        //Map集合中用Map.of方法创建时,必须保证key是不重复的
        //Map集合中用Map.of方法创建时,最多传递20个参数,也就是10个entry对象

        //原因如下:
        //在List和Set集合中,用of方法传递参数的形参是可变参数,也就是可以传递任意个的参数
        //而在Map集合中,of方法重载了10次,最多的参数只支持10个键值对对象的参数
        //因为一个参数列表中最多只能有一个可变参数,不支持键值对同时用可变参数来传递
        Map<String, Integer> map = Map.of("fuck1", 1, "fuck2", 2, "fuck3", 3);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        entries.forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));
    }
}
