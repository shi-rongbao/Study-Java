package com.shirongbao.spring6.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FirstSpringTest {
    @Test
    public void testFirstSpringCode(){
        // retrieve the Spring container object
        // ApplicationContext: 应用上下文, actually is spring container
        // ApplicationContext is an interface
        // ApplicationContext hava many implementation class, one of is ClassPathXmlApplicationContext
        // ClassPathXmlApplicationContext 专门从类路径当中加载spring配置文件的一个Spring上下文对象
        // 这行代码只要执行,就相当于启动了Spring容器,解析spring.xml文件,并且实例化所有的bean对象,放到spring容器当中.
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        // and you can use bean id getBean from spring container
        Object userBean = applicationContext.getBean("userBean");
        System.out.println(userBean);

        Object userDaoBean = applicationContext.getBean("userDaoBean");
        System.out.println(userDaoBean);

        // 自己使用log4j2记录日志信息
        // 第一步:创建日志记录器对象
        // 获取FirstSpringTest类的日志记录器对象,也就是说只要是FirstSpringTest类中的代码执行记录日志的话,就输出相关的日执信息
        Logger logger = LoggerFactory.getLogger(FirstSpringTest.class);
        logger.info("我是一条消息");
        logger.debug("我是一条调试消息");
        logger.error("我是一条错误消息");
    }
}
