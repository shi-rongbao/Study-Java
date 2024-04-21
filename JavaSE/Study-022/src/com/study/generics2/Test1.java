package com.study.generics2;

import java.util.ArrayList;

public class Test1 {
    public static void main(String[] args) {
        ArrayList<Cat> list = new ArrayList<>();
        list.add(new BoSiMao("Sakura",7));
        list.add(new LiHuaCat("SaSuki",6));
        keepPet(list);
    }

    public static void keepPet(ArrayList<? extends Cat> list){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).eat();
        }
    }
}
