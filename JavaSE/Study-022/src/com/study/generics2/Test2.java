package com.study.generics2;

import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<Dog> list = new ArrayList<>();
        list.add(new TaiDi("MiNaTo",8));
        list.add(new HaShiQi("Naruto",5));
        keepPet(list);
    }
    public static void keepPet(ArrayList<Dog> list){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).eat();
        }
    }
}
