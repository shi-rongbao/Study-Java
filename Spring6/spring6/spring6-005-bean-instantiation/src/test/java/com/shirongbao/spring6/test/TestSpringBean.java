package com.shirongbao.spring6.test;

import com.shirongbao.spring6.bean.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class TestSpringBean {
    @Test
    public void testDateNow(){
        Date date = new Date();
        System.out.println(date);
    }
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    @Test
    public void testDate() {
        Student student = applicationContext.getBean("studentBean", Student.class);
        System.out.println(student);
    }

    @Test
    public void testInstantiation4() {
        Person person = applicationContext.getBean("personBean", Person.class);
        System.out.println(person);
    }

    /**
     * 工厂方法模式获得bean对象
     * 配置具体工厂角色的bean
     * 配置具体产品角色的bean
     * 在具体产品角色的bean中指定id factory-bean and factory-method
     * factory-bean 是指向具体工厂角色的bean的id
     * factory-method  是指向具体工厂角色中new具体产品的对象的方法
     */
    @Test
    public void testInstantiation3() {
        Gun gun = applicationContext.getBean("gunBean", Gun.class);
        System.out.println(gun);
    }

    /**
     * 简单工厂模式获得bean对象
     * 配置简单工厂角色的bean,指定bean的id和class和factory-method
     * id  就作为applicationContext.getBean中的参数,获取具体产品角色
     * class   为简单工厂中具体工厂角色的的路径
     * factory-method  为具体工厂角色中的静态方法
     */
    @Test
    public void testInstantiation2() {
        Star starBean = applicationContext.getBean("starBean", Star.class);
        System.out.println(starBean);
    }

    /**
     * 通过构造方法获得bean对象
     */
    @Test
    public void testInstantiation1() {
        SpringBean sBean = applicationContext.getBean("sBean", SpringBean.class);
        System.out.println(sBean);
    }
}
