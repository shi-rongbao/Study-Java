package com.study;

import java.util.Calendar;
import java.util.Date;

public class Test{
    public static void main(String[] args) {
        System.out.println(getDate());
    }


    public static Date getDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        return calendar.getTime();
    }
}
