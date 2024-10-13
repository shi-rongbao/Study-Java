package com.study.biginteger;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerDemo1 {
    public static void main(String[] args) {
        //字符串只能写整数
        BigInteger bi1 = new BigInteger("21000000000");

        System.out.println("==================");

        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            BigInteger bi2 = new BigInteger(4,r);
            System.out.println(bi2);
        }

        System.out.println("==================");

        //将输入的字符串数字按照指定的进制转换为10进制输出
        BigInteger bi3 =  new BigInteger("10010001001010",2);
        System.out.println(bi3);

        BigInteger bi4 = new BigInteger("1657862952",16);
        System.out.println(bi4);
    }
}
