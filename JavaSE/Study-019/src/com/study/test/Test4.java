package com.study.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test4 {
    public static void main(String[] args) throws ParseException {
        long time1 = System.currentTimeMillis();
        String birthday = "2003年9月22日";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date birth = sdf.parse(birthday);
        long time2 = birth.getTime();
        long time = time1 - time2;
        System.out.println(time / 1000 / 60 / 60 /24);
    }
}
