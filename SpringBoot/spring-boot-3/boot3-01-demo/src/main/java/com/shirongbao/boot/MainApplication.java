package com.shirongbao.boot;

import com.shirongbao.boot.bean.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author ShiRongbao
 * @create 2024/2/8 22:50
 * @description:
 */

@SpringBootApplication  // 该注解表示这是一个springboot应用
public class MainApplication {
    public static void main(String[] args) {
        // 返回管理的ioc容器
        var ioc = SpringApplication.run(MainApplication.class, args);
        // 获得所有被管理的bean的name
        // String[] names = ioc.getBeanDefinitionNames();
        // 遍历names

        /*for (String name : names) {
            System.out.println(name);
        }*/

        /*String[] name = ioc.getBeanNamesForType(FastsqlException.class);
        for (String s : name) {
            System.out.println(s);
        }

        Object user1 = ioc.getBean("user");
        Object user2 = ioc.getBean("user");

        System.out.println(user1 == user2);*/

        /*for (String s : ioc.getBeanNamesForType(Cat.class)) {
            System.out.println(s);
        }

        for (String s : ioc.getBeanNamesForType(Dog.class)) {
            System.out.println(s);
        }

        for (String s : ioc.getBeanNamesForType(User.class)) {
            System.out.println(s);
        }*/

        /*Pig pig = ioc.getBean(Pig.class);
        System.out.println(pig);*/

        /*Sheep sheep = ioc.getBean(Sheep.class);
        System.out.println(sheep);*/

        Person person = ioc.getBean(Person.class);
        System.out.println(person);

        /*Child child = ioc.getBean(Child.class);
        System.out.println(child);*/
    }
}
