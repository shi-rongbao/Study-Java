package com.study.mextends2;

public class Person {
    private String id;
    private String name;
    private int salary;

    public Person() {
    }

    public Person(String id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void work(){
        System.out.println("工作");
    }

    public void eat(){
        System.out.println("吃米饭");
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
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
     * @return salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * 设置
     * @param salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String toString() {
        return "Person{id = " + id + ", name = " + name + ", salary = " + salary + "}";
    }
}
