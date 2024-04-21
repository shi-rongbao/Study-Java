package com.shirongbao.spring6.test;

import com.shirongbao.spring6.Spring6Config;
import com.shirongbao.spring6.bean.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Spring6Config.class)
public class SpringJunit5Test {
    @Autowired
    private User user;

    @Test
    public void testUser() {
        System.out.println(user.getName());
    }
}
