package com.shirongbao.factory.method;

/**
 * 具体工厂角色
 */
public class DaggerFactory extends WeaponFactory{
    @Override
    public Weapon get() {
        return new Dagger();
    }
}
