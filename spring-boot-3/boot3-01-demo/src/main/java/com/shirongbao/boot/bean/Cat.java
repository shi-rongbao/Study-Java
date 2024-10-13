package com.shirongbao.boot.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/2/9 11:38
 * @description:
 */
@Data
public class Cat {
    private Integer age;
    private String name;

    public Cat() {
    }

    public Cat(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Cat{age = " + age + ", name = " + name + "}";
    }
}
