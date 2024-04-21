package com.study.test;

public class Girlfriend {
    private String name;
    private int age;
    private int high;

    public Girlfriend() {
    }

    public Girlfriend(String name, int age, int high) {
        this.name = name;
        this.age = age;
        this.high = high;
    }

    /**
     * 获取
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取
     *
     * @return high
     */
    public int getHigh() {
        return high;
    }

    /**
     * 设置
     *
     * @param high
     */
    public void setHigh(int high) {
        this.high = high;
    }

    public String toString() {
        return name + "\t" + age + "\t" + high;
    }
}
