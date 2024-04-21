package com.shirongbao.spring6.test;

import com.shirongbao.spring6.bean.User;
import com.shirongbao.spring6.dao.UserDao;
import com.shirongbao.spring6.dao.VipDao;
import com.shirongbao.spring6.service.CustomerService;
import com.shirongbao.spring6.service.OrderService;
import com.shirongbao.spring6.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDITest {
    @Test
    public void testSimpleType(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        User user = applicationContext.getBean("userBean", User.class);
        System.out.println(user);
    }
    @Test
    public void testSetDI2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        OrderService orderServiceBean = applicationContext.getBean("orderServiceBean", OrderService.class);
        orderServiceBean.generate();

        OrderService orderServiceBean2 = applicationContext.getBean("orderServiceBean2", OrderService.class);
        orderServiceBean2.generate();
    }
    @Test
    public void testSetDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDao = applicationContext.getBean("userDaoBean", UserDao.class);
        UserService userService = applicationContext.getBean("serviceBean", UserService.class);
        userService.saveUser();
    }

    @Test
    public void testConstructorDI() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        UserDao userDao = applicationContext.getBean("userDaoBean", UserDao.class);
        VipDao vipDao = applicationContext.getBean("vipDaoBean", VipDao.class);
        CustomerService customerService = applicationContext.getBean("csBean", CustomerService.class);
        customerService.save();

        ApplicationContext applicationContext2 = new ClassPathXmlApplicationContext("beans.xml");
        CustomerService csBean2 = applicationContext2.getBean("csBean2", CustomerService.class);
        csBean2.save();

        ApplicationContext applicationContext3 = new ClassPathXmlApplicationContext("beans.xml");
        CustomerService csBean3 = applicationContext2.getBean("csBean3", CustomerService.class);
        csBean2.save();
    }
}
