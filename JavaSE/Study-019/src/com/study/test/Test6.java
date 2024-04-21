package com.study.test;

import java.util.Calendar;
import java.util.Date;

public class Test6 {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.MARCH,1);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);

        Calendar c = Calendar.getInstance();
        c.set(2001, Calendar.JANUARY,1);
        c.add(Calendar.DAY_OF_YEAR,-1);
        int days = c.get(Calendar.DAY_OF_YEAR);
        System.out.println(days);

    }
}
