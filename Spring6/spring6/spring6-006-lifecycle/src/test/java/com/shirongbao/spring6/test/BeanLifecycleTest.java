package com.shirongbao.spring6.test;

import com.shirongbao.spring6.bean.Student;
import com.shirongbao.spring6.bean.User;
import com.shirongbao.spring6.bean.Vip;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifecycleTest {
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    @Test
    public void testRegisterBean() {
        Student student = new Student();
        System.out.println(student);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("studentBean", student);

        Student bean = factory.getBean("studentBean", Student.class);
        System.out.println(bean);
        System.out.println(bean == student);
    }

    @Test
    public void testBeanLifecycleSeven() {
        Vip vip = applicationContext.getBean("vip", Vip.class);
        System.out.println("第六步,使用Bean \\" + vip);

        // 注意:必须手动关闭Spring容器,这样Spring容器才会销毁Bean
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        context.close();
    }

    @Test
    public void testBeanLifecycleFive() {
        User user = applicationContext.getBean("user", User.class);
        System.out.println("第六步,使用Bean \\" + user);

        // 注意:必须手动关闭Spring容器,这样Spring容器才会销毁Bean
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        context.close();
    }
}
