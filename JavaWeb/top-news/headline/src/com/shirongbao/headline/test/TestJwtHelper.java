package com.shirongbao.headline.test;

import com.shirongbao.headline.util.JwtHelper;
import org.testng.annotations.Test;

public class TestJwtHelper {
    @Test
    public void testAllMethod() throws InterruptedException {
        String token = JwtHelper.createToken(1L);
        Long userId = JwtHelper.getUserId(token);
        System.out.println(userId);
        System.out.println(JwtHelper.isExpiration(token));
        Thread.sleep(5100);
        System.out.println(JwtHelper.isExpiration(token));
    }
}
