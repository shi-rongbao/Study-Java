package com.study;

public class Student1 implements Comparable<Student1>{
    private int score;
    private int Chinese;
    private int math;
    private int English;
    private String name;
    private int age;

    public Student1() {
    }

    public Student1(int score, int Chinese, int math, int English, String name, int age) {
        this.score = score;
        this.Chinese = Chinese;
        this.math = math;
        this.English = English;
        this.name = name;
        this.age = age;
    }

    /**
     * 获取
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * 设置
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 获取
     * @return Chinese
     */
    public int getChinese() {
        return Chinese;
    }

    /**
     * 设置
     * @param Chinese
     */
    public void setChinese(int Chinese) {
        this.Chinese = Chinese;
    }

    /**
     * 获取
     * @return math
     */
    public int getMath() {
        return math;
    }

    /**
     * 设置
     * @param math
     */
    public void setMath(int math) {
        this.math = math;
    }

    /**
     * 获取
     * @return English
     */
    public int getEnglish() {
        return English;
    }

    /**
     * 设置
     * @param English
     */
    public void setEnglish(int English) {
        this.English = English;
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
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Student1{score = " + score + ", Chinese = " + Chinese + ", math = " + math + ", English = " + English + ", name = " + name + ", age = " + age + "}";
    }

    @Override
    public int compareTo(Student1 o) {
        return 0;
    }
}
