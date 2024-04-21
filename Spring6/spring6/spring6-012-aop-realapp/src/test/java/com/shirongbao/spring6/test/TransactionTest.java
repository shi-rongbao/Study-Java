package com.shirongbao.spring6.test;

import com.shirongbao.spring6.service.AccountService;
import com.shirongbao.spring6.service.OrderService;
import com.shirongbao.spring6.service.Spring6Config;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionTest {
    @Test
    public void testTransaction(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spring6Config.class);
        OrderService order = context.getBean("orderService", OrderService.class);
        AccountService account = context.getBean("accountService", AccountService.class);
        order.generate();
        System.out.println("=========================");
        order.cancel();
        System.out.println("=========================");

        account.transfer();
        System.out.println("=========================");
        account.withdraw();
        System.out.println("=========================");
    }
}
