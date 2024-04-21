package com.shirongbao.spring6.bean;

// 这是具体工厂角色
public class GunFactory {
    public Gun get() {
        return new Gun();
    }
}
