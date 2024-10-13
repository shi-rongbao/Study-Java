package com.study.datetumeformatter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterDemo1 {
    public static void main(String[] args) {
        ZonedDateTime time = Instant.now().atZone(ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd EE a HH:mm:ss");
        String timeStr = dtf1.format(time);
        System.out.println(timeStr);
    }
}
