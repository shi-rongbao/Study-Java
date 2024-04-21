package com.shirongbao.simple.factory;

public class Fighter extends Weapon{
    @Override
    public void attack() {
        System.out.println("战斗机抛下炸弹");
    }
}
