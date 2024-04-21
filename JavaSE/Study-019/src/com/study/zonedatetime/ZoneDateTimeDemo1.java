package com.study.zonedatetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneDateTimeDemo1 {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        ZonedDateTime time = ZonedDateTime.of(2023,8,1,8,55,0,0, ZoneId.of("Asia/Shanghai"));
        System.out.println(time);

        Instant instant = Instant.ofEpochMilli(0L);
        ZonedDateTime time1 = ZonedDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        System.out.println(time1);

        ZonedDateTime zonedDateTime = time1.withYear(2023);
        System.out.println(time1);
        System.out.println(zonedDateTime);

    }
}
