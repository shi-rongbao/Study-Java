package com.study.localdate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateDemo1 {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDate birthDate = LocalDate.of(2023,9,22);
        System.out.println(birthDate);

        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(now2);

        LocalTime time = LocalTime.now();
        System.out.println(time);

        System.out.println("================================");

        LocalDateTime birthDay = LocalDateTime.of(2003,9,22,0,0,0);

        Duration between = Duration.between(birthDay, now);
        System.out.println(between);
        System.out.println(between.toDays());//出生到现在7253天

    }
}
