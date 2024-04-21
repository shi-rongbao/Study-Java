package com.shirongbao.spring6.test;

import com.shirongbao.spring6.biz.Spring6Config;
import com.shirongbao.spring6.biz.UserService;
import com.shirongbao.spring6.biz.VipService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SecurityTest {
    @Test
    public void testSecurity(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spring6Config.class);
        UserService user = context.getBean("userService", UserService.class);
        VipService vip = context.getBean("vipService", VipService.class);
        user.saveUser();
        user.deleteUser();
        user.modifyUser();
        user.getUser();

        vip.saveVip();
        vip.deleteVip();
        vip.modifyVip();
        vip.getVip();
    }
}
