package com.study.chronounit;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitDemo {
    public static void main(String[] args) {
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime birthday = LocalDateTime.of(2003,9,22,0,0);
        long year = ChronoUnit.YEARS.between(birthday, timeNow);
        System.out.println(year);
        long day = ChronoUnit.DAYS.between(birthday, timeNow);
        System.out.println(day);
    }
}
