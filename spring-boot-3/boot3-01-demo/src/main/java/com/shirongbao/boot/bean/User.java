package com.shirongbao.boot.bean;

import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/2/9 11:22
 * @description:
 */
public class User {
    private Integer age;
    private String username;

    public User() {
    }

    public User(Integer age, String username) {
        this.age = age;
        this.username = username;
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return "User{age = " + age + ", username = " + username + "}";
    }
}
