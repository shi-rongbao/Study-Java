package com.study.calendar;

import java.util.Calendar;

public class CalendarDemo1 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        System.out.println(c);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int week = c.get(Calendar.DAY_OF_WEEK);
        String[] weekArr = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        System.out.println(week);
        System.out.println();
        System.out.println(year + " " + (month + 1) + " " + day + " " + weekArr[week]);

    }
}
