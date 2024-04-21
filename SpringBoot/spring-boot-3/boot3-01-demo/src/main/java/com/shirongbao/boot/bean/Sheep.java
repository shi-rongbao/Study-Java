package com.shirongbao.boot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ShiRongbao
 * @create 2024/2/10 10:53
 * @description:
 */
@ConfigurationProperties(prefix = "sheep")
public class Sheep {
    private Long id;
    private String name;
    private Integer age;

    public Sheep() {
    }

    public Sheep(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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

    public String toString() {
        return "Sheep{id = " + id + ", name = " + name + ", age = " + age + "}";
    }
}

