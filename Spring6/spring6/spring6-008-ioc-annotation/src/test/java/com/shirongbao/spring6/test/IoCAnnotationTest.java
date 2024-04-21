package com.shirongbao.spring6.test;

import cn.shirongbao.Spring6Config;
import cn.shirongbao.service.StudentService;
import com.shirongbao.spring6.bean.Order;
import com.shirongbao.spring6.bean.Student;
import com.shirongbao.spring6.bean.User;
import com.shirongbao.spring6.bean.Vip;
import org.junit.Test;
import org.shirongbao.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCAnnotationTest {
    @Test
    public void testNoXML(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spring6Config.class);
        StudentService service = context.getBean("studentService", StudentService.class);
        service.deleteStudent();
    }
    @Test
    public void testResource(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-resource.xml");
        StudentService service = applicationContext.getBean("studentService", StudentService.class);
        service.deleteStudent();
    }
    @Test
    public void testAutoWired(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-autowired.xml");
        applicationContext.getBean("orderService", OrderService.class).generate();
    }
    @Test
    public void testChoose(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-choose.xml");
    }
    @Test
    public void testBeanComponent(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Vip vip = applicationContext.getBean("vipBean", Vip.class);
        System.out.println(vip);

        User user = applicationContext.getBean("userBean", User.class);
        System.out.println(user);

        Order order = applicationContext.getBean("orderBean", Order.class);
        System.out.println(order);

        Student student = applicationContext.getBean("studentBean", Student.class);
        System.out.println(student);
    }
}
