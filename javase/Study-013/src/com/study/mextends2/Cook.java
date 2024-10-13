package com.study.mextends2;

public class Cook extends Person{
    @Override
    public void work() {
        System.out.println("炒菜");
    }

    public Cook() {
    }

    public Cook(String id, String name, int salary) {
        super(id, name, salary);
    }

}
