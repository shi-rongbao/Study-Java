package com.shirongbao.spring6.test;

import com.shirongbao.spring6.Spring6Config;
import com.shirongbao.spring6.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Spring6Config.class)
public class SpringJunit4Test {
    @Autowired
    private User user;
    @Test
    public void testUser(){
        System.out.println(user.getName());
    }
}
