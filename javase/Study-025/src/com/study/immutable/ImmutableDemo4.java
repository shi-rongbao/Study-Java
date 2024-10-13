package com.study.immutable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ImmutableDemo4 {
    public static void main(String[] args) {
        HashMap<String,Integer> hm = new HashMap<>();

        hm.put("农夫山泉",3);
        hm.put("冰红茶",3);
        hm.put("维他命水",5);
        hm.put("冰峰",2);
        hm.put("绿茶",3);
        hm.put("茉莉蜜茶",3);
        hm.put("茉莉花成",3);
        hm.put("红牛",6);
        hm.put("矿泉水",1);
        hm.put("可口可乐",3);
        hm.put("百事可乐",3);
        hm.put("美年达",3);
        hm.put("王老吉",4);
        hm.put("苏打水",5);

        Set<Map.Entry<String, Integer>> entries = hm.entrySet();
        //先创建一个Map对象的数组,数组长度为0
        Map.Entry[] arr1 = new Map.Entry[0];
        //再将entries中的元素放入数组中
        Map.Entry[] arr2 = entries.toArray(arr1);//这里底层会判断,arr1数组能否放得下entries中的元素,如果放不下,就再创建一个新的数组,如果放得下,就使用原来的数组
//最后可以使用Map.ofEntries()方法,将entry对象放入Map集合中
        //!!!!!可变参数的底层是数组!!!!!

        //不可变的Map集合
        Map<String,Integer> map = Map.ofEntries(arr2);

        //一行搞定的方法:
        Map<String,Integer> map1 = Map.ofEntries(hm.entrySet().toArray(new Map.Entry[0]));

        //JDK10后的新特性
        //copyOf方法,传递一个集合过去,如果传递的集合本身就是不可变集合,那就返回传递的集合,如果不是不可变集合,则将他转变为不可变集合,所用到的逻辑与上面所编写的一致
        Map<String,Integer> map2 = Map.copyOf(hm);
    }
}
