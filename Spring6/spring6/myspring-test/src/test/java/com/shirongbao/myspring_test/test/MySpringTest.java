package com.shirongbao.myspring_test.test;

import com.shirongbao.myspring_test.bean.OrderService;
import com.shirongbao.myspring_test.bean.User;
import org.junit.Test;
import org.myspringframework.core.ApplicationContext;
import org.myspringframework.core.ClassPathXmlApplicationContext;

public class MySpringTest {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User user = (User) applicationContext.getBean("userBean");
        OrderService service = (OrderService) applicationContext.getBean("orderService");
        service.save();
        System.out.println(user);
    }
}
