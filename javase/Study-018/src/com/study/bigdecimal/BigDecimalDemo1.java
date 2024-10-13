package com.study.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalDemo1 {
    public static void main(String[] args) {
        BigDecimal bd1 = new BigDecimal("3.0");
        BigDecimal bd2 = new BigDecimal(0.1);
        System.out.println(bd1);
        System.out.println(bd2);

        System.out.println("==============");

        BigDecimal bd3 = BigDecimal.valueOf(0.1);
        System.out.println(bd3);

        System.out.println("==============");

        System.out.println(bd1.add(bd3));

        System.out.println(bd1.add(bd3) == bd1);

        BigDecimal bd4 = new BigDecimal("10.0");
        System.out.println(bd4.divide(bd1,2, RoundingMode.HALF_UP));


    }
}
