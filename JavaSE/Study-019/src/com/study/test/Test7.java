package com.study.test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Test7 {
    public static void main(String[] args) {
        LocalDate ld1 = LocalDate.of(2001,3,1);
        LocalDate date = ld1.minusDays(1);
        System.out.println(date);

        boolean leapYear = ld1.isLeapYear();
        System.out.println(leapYear);
    }
}
