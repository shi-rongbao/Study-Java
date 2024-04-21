package com.study.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo2 {
    public static void main(String[] args) throws ParseException {
        String time = "2023-09-22 5:20:00";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf1.parse(time);
        System.out.println(d1);
    }
}
