package com.shirongbao.mybatis.pojo;

import java.util.Date;

public class Student {
    private Long id;
    private String name;
    private Integer age;
    private Double height;
    private Date birth;
    private Character sex;

    public Student() {
    }

    public Student(Long id, String name, Integer age, Double height, Date birth, Character sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.birth = birth;
        this.sex = sex;
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

    /**
     * 获取
     * @return height
     */
    public Double getHeight() {
        return height;
    }

    /**
     * 设置
     * @param height
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 获取
     * @return birth
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * 设置
     * @param birth
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * 获取
     * @return sex
     */
    public Character getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String toString() {
        return "Student{id = " + id + ", name = " + name + ", age = " + age + ", height = " + height + ", birth = " + birth + ", sex = " + sex + "}";
    }
}
