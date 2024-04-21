package com.study.instant;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantDemo1 {
    public static void main(String[] args) {


        System.out.println("===========================");

        Instant instant1 = Instant.ofEpochMilli(0L);
        System.out.println(instant1);

        System.out.println("===========================");

        Instant instant2 = Instant.ofEpochSecond(1L);
        System.out.println(instant2);

        System.out.println("===========================");

        Instant instant3 = Instant.ofEpochSecond(1L,1000000000L);
        System.out.println(instant3);

        System.out.println("===========================");

        Instant now = Instant.now();
        System.out.println(now);
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);

        System.out.println("===========================");

        Instant instant4 = Instant.ofEpochMilli(0L);
        Instant instant5 = Instant.ofEpochMilli(1000L);

        boolean after = instant4.isAfter(instant5);
        System.out.println(after);

        System.out.println("===========================");

        Instant instant = Instant.ofEpochMilli(0L);
        Instant instant6 = instant.minusMillis(1000L);
        System.out.println(instant6);
        Instant instant7 = instant.plusSeconds(1L);
        System.out.println(instant7);
    }
}
