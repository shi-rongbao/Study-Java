package com.shirongbao.spring6.test;

import com.shirongbao.spring6.reflect.SomeService;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    @Test
    public void test() throws Exception{
        /*
            需求:
                假设现在已知以下信息:
                    1.  有这样一个类,类名叫做:com.shirongbao.spring6.reflect.User
                    2.  这个类符合javabean规范.属性私有化,对外提供setter和getter方法.
                    3.  你还知道类中有一个属性的名字是 age.
                    4.  并且你还知道age属性是int类型
                请使用反射机制调用相关方法,给User对象的age属性赋值.
         */

        Class<?> clazz = Class.forName("com.shirongbao.spring6.reflect.User");
        Object user = clazz.getDeclaredConstructor().newInstance();
        Field field = clazz.getDeclaredField("age");
        Method setAgeMethod = clazz.getDeclaredMethod("setAge", field.getType());
        setAgeMethod.invoke(user, 20);
        System.out.println(user);
        Method getAgeMethod = clazz.getDeclaredMethod("getAge");
        Object age = getAgeMethod.invoke(user);
        System.out.println(age);
    }

    @Test
    public void testReflect() throws Exception {
//        Class<?> clazz = Class.forName("com.shirongbao.spring6.reflect.SomeService");
//        Method doSome = clazz.getDeclaredMethod("doSome", String.class, int.class);
//        Constructor<?> constructor = clazz.getDeclaredConstructor();
//        Object service = constructor.newInstance();
//        Object fuck = doSome.invoke(service, "fuck", 5);
//        System.out.println(fuck);
//        Class<?> clazz = Class.forName("com.shirongbao.spring6.reflect.SomeService");
//        Method doSome = clazz.getDeclaredMethod("doSome", String.class);
//        Object service = clazz.getDeclaredConstructor().newInstance();
//        Object result = doSome.invoke(service, "张三");
//        System.out.println(result);
        Class<?> clazz = Class.forName("com.shirongbao.spring6.reflect.SomeService");
        Method doSomeMethod = clazz.getDeclaredMethod("doSome");
        Object service = clazz.getDeclaredConstructor().newInstance();
        doSomeMethod.invoke(service);
    }

    @Test
    public void testNormal() {
        new SomeService().doSome();

        String s1 = new SomeService().doSome("fuck");
        System.out.println(s1);

        String s2 = new SomeService().doSome("fuck", 5);
        System.out.println(s2);
    }
}
