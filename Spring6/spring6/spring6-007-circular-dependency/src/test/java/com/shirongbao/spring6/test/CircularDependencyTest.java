package com.shirongbao.spring6.test;

import com.shirongbao.spring6.bean.Husband;
import com.shirongbao.spring6.bean.Wife;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CircularDependencyTest {
    @Test
    public void testCD() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println(husband);
        System.out.println(wife);
    }
}
