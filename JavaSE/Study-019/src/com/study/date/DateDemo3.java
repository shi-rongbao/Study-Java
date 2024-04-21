package com.study.date;

import java.util.Date;
import java.util.Random;

public class DateDemo3 {
    public static void main(String[] args) {
        Random r = new Random();
        Date d1 = new Date(Math.abs(r.nextInt()));
        System.out.println(d1);
        Date d2 = new Date(Math.abs(r.nextInt()));
        System.out.println(d2);
        long time = d1.getTime() - d2.getTime();
        System.out.println(time);
    }
}
