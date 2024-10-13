package com.study.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo3 {
    public static void main(String[] args) throws ParseException {
        String birth = "2000-11-11";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf1.parse(birth);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
        String str = sdf2.format(d1);
        System.out.println(str);
    }
}
