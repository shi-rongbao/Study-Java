package com.study.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo4 {
    public static void main(String[] args) throws ParseException {
        String start = "2023年11月11日 0:0:0";
        String end = "2023年11月11日 0:10:0";

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date d1 = sdf1.parse(start);
        Date d2 = sdf1.parse(end);
        long startTime = d1.getTime();
        long endTime = d2.getTime();

        String jiaTime = "2023年11月11日 0:01:0";
        String piTime = "2023年11月11日 0:11:0";
        Date d3 = sdf1.parse(jiaTime);
        Date d4 = sdf1.parse(piTime);
        long jiaTimeBy = d3.getTime();
        long piTimeBy = d4.getTime();

        if (jiaTimeBy >= startTime && jiaTimeBy <= endTime) {
            System.out.println("小贾抢购成功");
        }else {
            System.out.println("小贾抢购失败");
        }
        if(piTimeBy >= startTime && piTimeBy <= endTime) {
            System.out.println("小皮抢购成功");
        }else {
            System.out.println("小皮抢购失败");
        }
    }
}
