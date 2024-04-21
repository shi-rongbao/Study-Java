package com.study.date;

import java.util.Date;

public class DateDemo2 {
    public static void main(String[] args) {
        Date d1 = new Date(0L);
        long time = d1.getTime();
        time += 1000L * 60 * 60 * 24 *365;
        d1.setTime(time);
        System.out.println(d1);
        System.out.println("==================");
        Date d2 = new Date(time);
        System.out.println(d2);
    }
}
