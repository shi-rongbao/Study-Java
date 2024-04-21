package com.shirongbao.factory.method;

public class Test {
    public static void main(String[] args) {
        WeaponFactory weaponFactory1 = new DaggerFactory();
        Weapon dagger = weaponFactory1.get();
        dagger.attack();

        WeaponFactory weaponFactory2 = new GunFactory();
        Weapon gun = weaponFactory2.get();
        gun.attack();
    }
}
