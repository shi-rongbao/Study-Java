package com.study.doudizhu2;

import java.util.*;

public class PokerGame {
    static HashMap<Integer, String> hm = new HashMap<>();
    static ArrayList<Integer> list = new ArrayList<>();

    static {
        String[] color = {"♥", "♦", "♠", "♣"};//定义花色数组
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};//定义牌数字数组
        int serialNumber = 1;//定义序号变量
        for (String n : number) {
            for (String c : color) {
                hm.put(serialNumber, c + n);//将序号与牌添加到hm集合中
                list.add(serialNumber);//添加序号到list集合中
                serialNumber++;//每添加一个,序号自增.
            }
        }
        //添加大王小王所对应的序号
        hm.put(serialNumber,"小王");
        list.add(serialNumber);//添加序号到list集合中
        serialNumber++;
        hm.put(serialNumber,"大王");
        list.add(serialNumber);//添加序号到list集合中


    }

    public PokerGame() {

        Collections.shuffle(list);//打乱牌的顺序,洗牌

        //创建底牌与三位玩家发牌所对应的集合
        //用TreeSet集合可以自动排序
        TreeSet<Integer> lord = new TreeSet<>();
        TreeSet<Integer> player1 = new TreeSet<>();
        TreeSet<Integer> player2 = new TreeSet<>();
        TreeSet<Integer> player3 = new TreeSet<>();

        //遍历list集合,发牌
        for (int i = 0; i < list.size(); i++) {
            if (i <= 2){
                lord.add(list.get(i));
                continue;
            }
            if (i % 3 == 0){
                player1.add(list.get(i));
            }else if (i % 3 == 1){
                player2.add(list.get(i));
            }else {
                player3.add(list.get(i));
            }
        }

        lookPoker("底牌",lord);
        lookPoker("时荣宝",player1);
        lookPoker("杨鹏程",player2);
        lookPoker("柯旗纬",player3);
    }

    public static void lookPoker(String name, TreeSet<Integer> ts){
        StringBuilder sb = new StringBuilder();
        for (Integer serialNumber : ts) {
            sb.append(hm.get(serialNumber));
            sb.append(" ");
        }
        System.out.println(name + ":" + sb);
    }
}
