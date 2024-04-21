package com.shirongbao.spring6.test;

import com.shirongbao.spring6.bean.SpringBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanScopeTest {
    @Test
    public void testThreadScope() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-scope.xml");
        SpringBean bean1 = applicationContext.getBean("springBean", SpringBean.class);
        SpringBean bean2 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(bean1);
        System.out.println(bean2);

        new Thread(() -> {
            SpringBean bean3 = applicationContext.getBean("springBean", SpringBean.class);
            SpringBean bean4 = applicationContext.getBean("springBean", SpringBean.class);
            System.out.println(bean3);
            System.out.println(bean4);
        }).start();
    }

    @Test
    public void testBeanScope() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-scope.xml");
        SpringBean bean = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(bean);

        SpringBean bean2 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(bean2);

        SpringBean bean3 = applicationContext.getBean("springBean", SpringBean.class);
        System.out.println(bean3);
    }
}
