package com.study.mpolymorphism2;

public class Person {
    private String name;
    private int age;
    Animal a = new Animal();

    public void keepPet(Animal a, String something) {
        if (a instanceof Dog) {
            Dog dog = (Dog) a;
            System.out.println("年龄为" + age + "岁的" + name + "养了一只" + a.getColor() + "颜色的" + a.getAge() + "岁的狗");
            dog.eat(something);
        } else if (a instanceof Cat) {
            Cat cat = (Cat) a;
            System.out.println("年龄为" + age + "岁的" + name + "养了一只" + a.getColor() + "颜色的" + a.getAge() + "岁的猫");
            cat.eat(something);
        }else {
            System.out.println("没有这种动物");
        }

        String s = "abc";
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
