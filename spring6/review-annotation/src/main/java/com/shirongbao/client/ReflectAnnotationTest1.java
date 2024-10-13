package com.shirongbao.client;

import com.shirongbao.annotation.Component;

public class ReflectAnnotationTest1 {
    public static void main(String[] args) throws Exception{
        // 通过反射机制怎么读取注解

        // 获取类
        Class<?> aClass = Class.forName("com.shirongbao.bean.User");

        // 判断类中有没有Component注解
        if (aClass.isAnnotationPresent(Component.class)) {
            // 如果有就获取到这个注解
            Component annotation = aClass.getAnnotation(Component.class);
            System.out.println(annotation.value());
        }
    }
}
