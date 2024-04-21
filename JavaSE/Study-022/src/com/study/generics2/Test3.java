package com.study.generics2;

import java.util.ArrayList;
public class Test3 {
    public static void main(String[] args) {
        ArrayList<Animal> list = new ArrayList<>();
        list.add(new TaiDi("MiNaTo",8));
        list.add(new HaShiQi("Naruto",5));
        list.add(new BoSiMao("Sakura",7));
        list.add(new LiHuaCat("SaSuki",6));
        keepPet(list);
    }
    public static void keepPet(ArrayList<? extends Animal> list){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).eat();
        }
    }
}
