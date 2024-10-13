package com.study.simpledateformat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo1 {
    public static void main(String[] args) {
        Date d1 = new Date(0L);
        SimpleDateFormat sdf1 = new SimpleDateFormat();
        String str1 = sdf1.format(d1);
        System.out.println(str1);

        Date d3 = new Date();

        Date d2 = new Date(0L);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EE");
        String str2 = sdf2.format(d2);
        System.out.println(str2);
    }
}
