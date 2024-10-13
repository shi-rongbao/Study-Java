package com.study;

import com.study.exception.AgeOutOfBoundsException;
import com.study.exception.NameFormatException;

public class Girlfriend {
    private String name;
    private int age;

    public Girlfriend() {
    }

    public Girlfriend(String name, int age) {
        this.name = name;
        this.age = age;
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
        int len = name.length();
        if (len < 1 || len > 5) {
            throw new NameFormatException(name + "姓名格式化异常");
        }
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
        if (age < 16 || age > 25) {
            throw new AgeOutOfBoundsException(age + "年龄范围异常");
        }
        this.age = age;
    }

    public String toString() {
        return "Girlfriend{name = " + name + ", age = " + age + "}";
    }
}
