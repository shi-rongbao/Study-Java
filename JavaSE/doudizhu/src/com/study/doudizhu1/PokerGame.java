package com.study.doudizhu1;

import java.util.ArrayList;
import java.util.Collections;

public class PokerGame {
    static ArrayList<String> list = new ArrayList<>();//创建牌盒集合

    static {
        String[] color = {"♥", "♦", "♠", "♣"};//定义花色数组
        String[] number = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};//定义牌数字数组
        for (String c : color) {
            for (String n : number) {
                list.add(c + n);//将花色与数字组合添加到牌盒中
            }
        }
        list.add("小王");
        list.add("大王");
    }

    public PokerGame() {
        Collections.shuffle(list);//打乱牌的顺序

        //创建存储底牌,三个玩家手中牌的集合
        ArrayList<String> lord = new ArrayList<>();
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();

        //循环发牌,前三张牌发送到底牌集合中
        //从第四张牌开始,依次给三位玩家发牌
        for (int i = 0; i < list.size(); i++) {
            String poker = list.get(i);//获取到当前牌
            if (i <= 2){
                lord.add(poker);
                continue;
            }
            if (i % 3 == 0){
                player1.add(poker);
            }else if (i % 3 == 1){
                player2.add(poker);
            }else {
                player3.add(poker);
            }
        }
        lookPoker("底牌",lord);
        lookPoker("时荣宝",player1);
        lookPoker("杨鹏程",player2);
        lookPoker("柯旗纬",player3);
    }

    public static void lookPoker(String name, ArrayList<String> list){
        StringBuilder sb = new StringBuilder();
        for (String poker : list) {
            sb.append(poker);
            sb.append(" ");
        }
        System.out.println(name + ":" + sb);
    }
}
