package com.shirongbao.boot.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/2/9 11:40
 * @description:
 */
@Data
public class Dog {
    private Integer age;
    private String name;

    public Dog() {
    }

    public Dog(Integer age, String name) {
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
        return "Dog{age = " + age + ", name = " + name + "}";
    }
}
