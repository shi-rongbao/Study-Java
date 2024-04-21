package com.shirongbao.spring6.bean;

public class User {
    private Integer id;
    private String realName;
    private Integer age;

    public User() {
    }

    public User(Integer id, String realName, Integer age) {
        this.id = id;
        this.realName = realName;
        this.age = age;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
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
        return "User{id = " + id + ", realName = " + realName + ", age = " + age + "}";
    }
}
