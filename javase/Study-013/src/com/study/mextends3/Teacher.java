package com.study.mextends3;

public class Teacher extends Employee{
    public Teacher() {
    }

    public Teacher(String id, String name) {
        super(id, name);
    }

    @Override
    public void work(){
        System.out.println("教研部工作");
    }
}
