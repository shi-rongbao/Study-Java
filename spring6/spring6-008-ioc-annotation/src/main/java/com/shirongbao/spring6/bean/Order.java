package com.shirongbao.spring6.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("orderBean")
public class Order {

    @Value("张三")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                '}';
    }
}
