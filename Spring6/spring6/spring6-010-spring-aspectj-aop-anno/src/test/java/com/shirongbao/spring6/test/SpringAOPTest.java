package com.shirongbao.spring6.test;

import com.shirongbao.spring6.service.OrderService;
import com.shirongbao.spring6.service.Spring6Config;
import org.aspectj.weaver.patterns.NotAnnotationTypePattern;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAOPTest {
    @Test
    public void testNoXML(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spring6Config.class);
        OrderService service = context.getBean("orderService", OrderService.class);
        service.generate();
    }
    @Test
    public void testBefore() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        UserService service = applicationContext.getBean("userService", UserService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
//        service.login();
//        service.logout();
        orderService.generate();
    }
}
