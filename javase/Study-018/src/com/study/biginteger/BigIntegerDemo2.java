package com.study.biginteger;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerDemo2 {
    public static void main(String[] args) {
        BigInteger bi1 = BigInteger.valueOf(10);
        BigInteger bi2 = BigInteger.valueOf(7);

        System.out.println(bi1.add(bi2));

        BigInteger bi3 = BigInteger.valueOf(14);
        BigInteger bi4 = BigInteger.valueOf(3);
        BigInteger[] bigIntegers = bi3.divideAndRemainder(bi4);
        for (int i = 0; i < bigIntegers.length; i++) {
            System.out.print(bigIntegers[i] + " ");
        }
        System.out.println(bigIntegers);


    }
}
