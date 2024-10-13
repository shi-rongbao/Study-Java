package com.study.doudizhu3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class PokerGame {

    static ArrayList<String> list = new ArrayList<>();//牌盒
    static HashMap<String, Integer> hm = new HashMap<>();

    //准备牌
    static {
        //给每张牌赋予价值,只添加特殊牌的价值
        hm.put("J", 11);
        hm.put("Q", 12);
        hm.put("K", 13);
        hm.put("A", 14);
        hm.put("2", 15);
        hm.put("小王", 100);
        hm.put("大王", 1000);


        String[] color = {"♥", "♦", "♠", "♣"};//定义花色数组
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};//定义牌数字数组

        for (String c : color) {
            for (String n : number) {
                list.add(c + n);
            }
        }

        list.add("小王");
        list.add("大王");

    }

    public PokerGame() {

        //洗牌
        Collections.shuffle(list);

        //发牌
        //创建四个集合,分别用来存储底牌与三个玩家手中的牌
        ArrayList<String> lord = new ArrayList<>();
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();

        //发牌,遍历list集合
        for (int i = 0; i < list.size(); i++) {
            if (i <= 2) {
                lord.add(list.get(i));
                continue;
            }
            if (i % 3 == 0) {
                player1.add(list.get(i));
            } else if (i % 3 == 1) {
                player2.add(list.get(i));
            } else {
                player3.add(list.get(i));
            }
        }
        order(lord);
        order(player1);
        order(player2);
        order(player3);
        lookPoker("底牌", lord);
        lookPoker("时荣宝", player1);
        lookPoker("杨鹏程", player2);
        lookPoker("柯旗纬", player3);
    }

    //查看底牌方法, 遍历每个玩家牌的集合, 字符串拼接
    public static void lookPoker(String name, ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
            sb.append(" ");
        }
        System.out.println(name + ":" + sb);
    }

    public void order(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //o1 当前要插入的元素
                //o2 当前已经有序的元素
                String color1 = o1.substring(0, 1);
                int value1 = getValue(o1);

                String color2 = o2.substring(0, 1);
                int value2 = getValue(o2);

                int i = value1 - value2;

                return i == 0 ? color1.compareTo(color2) : i;
            }
        });
    }

    public int getValue(String poker){
        //如果牌是大王或者小王,直接返回牌的价值
        if (poker.equals("大王") || poker.equals("小王")){
            return hm.get(poker);
        }
        //如果牌不是大王或者小王,截取牌的数字,判断是否在map集合中,如果存在,返回价值,如果不存在,返回对应的数字作为牌的价值
        String number = poker.substring(1);
        if (hm.containsKey(number)){
            return hm.get(number);
        }else {
            return Integer.parseInt(number);
        }
    }
}
