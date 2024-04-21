package com.shirongbao.schedule.test;

import com.shirongbao.schedule.util.MD5Util;
import org.testng.annotations.Test;

public class TestMD5Util {

    @Test
    public void testEncrypt(){
        String encrypt = MD5Util.encrypt("fuckyou");
        System.out.println(encrypt);
    }
}
