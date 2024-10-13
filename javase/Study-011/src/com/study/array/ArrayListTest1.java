package com.study.array;

import java.util.ArrayList;

public class ArrayListTest1 {
    public static void main(String[] args) {
        /*
            需求:定义一个集合,添加字符串,并进行遍历
                遍历格式参照:[元素1,元素2,元素3]
         */
        ArrayList<String> list = new ArrayList<>();
        list.add("fuck");
        list.add("you");
        list.add("mather");
        System.out.println(list);
    }
}
