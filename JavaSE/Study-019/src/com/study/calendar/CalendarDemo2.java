package com.study.calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo2 {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,24);
        Date d = calendar.getTime();
        System.out.println(d);
    }
}
