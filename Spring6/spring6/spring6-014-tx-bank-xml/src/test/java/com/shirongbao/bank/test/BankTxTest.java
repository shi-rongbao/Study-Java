package com.shirongbao.bank.test;

import com.shirongbao.bank.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BankTxTest {
    @Test
    public void testNoAnnotation() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AccountService service = applicationContext.getBean("accountService", AccountService.class);
        try {
            service.transfer("act-001", "act-002", 10000);
            System.out.println("转账成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
