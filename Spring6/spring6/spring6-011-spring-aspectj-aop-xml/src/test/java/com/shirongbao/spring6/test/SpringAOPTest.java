package com.shirongbao.spring6.test;

import com.shirongbao.spring6.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAOPTest {
    @Test
    public void testXML() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService service = applicationContext.getBean("userService", UserService.class);
        service.login();
    }
}
