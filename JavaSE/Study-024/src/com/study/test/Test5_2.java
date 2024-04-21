package com.study.test;

import java.util.*;

public class Test5_2 {
    public static void main(String[] args) {
        /*
            需求:
                定义一个Map集合,键用表示省份名称province,值表示市city
                但是市会有多个,遍历结果格式如下:

                江苏省 = 南京市, 扬州市, 苏州市, 无锡市, 常州市
                ...

         */

        Integer i1 = 127;

        Integer i3 = 128;
        Integer i2 = 127;


        System.out.println(i1 == i2);

        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "南京市", "扬州市", "苏州市", "常州市");

        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "西安市", "咸阳市", "榆林市", "宝鸡市");

        ArrayList<String> list3 = new ArrayList<>();
        Collections.addAll(list3, "合肥市", "蚌埠市", "宿州市", "黄山市");

        HashMap<String, ArrayList<String>> hm = new HashMap<>();

        hm.put("江苏省", list1);
        hm.put("陕西省", list2);
        hm.put("安徽省", list3);

        Set<Map.Entry<String, ArrayList<String>>> entries = hm.entrySet();
        for (Map.Entry<String, ArrayList<String>> entry : entries) {
            String province = entry.getKey();
            ArrayList<String> city = entry.getValue();
            StringJoiner sj = new StringJoiner(", ", "", "");
            for (String str : city) {
                sj.add(str);
            }
            System.out.println(province + " = " + sj);
        }
    }
}
