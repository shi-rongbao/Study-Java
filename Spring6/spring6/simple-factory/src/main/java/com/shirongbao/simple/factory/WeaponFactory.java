package com.shirongbao.simple.factory;

public class WeaponFactory {
    public static Weapon get(String weaponType) {
        if ("TANK".equals(weaponType)) {
            return new Tank();
        } else if ("DAGGER".equals(weaponType)) {
            return new Dagger();
        } else if ("FIGHTER".equals(weaponType)) {
            return new Fighter();
        } else {
            throw new RuntimeException("不支持该武器的生产");
        }
    }
}
