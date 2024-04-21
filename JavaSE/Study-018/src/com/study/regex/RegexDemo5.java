package com.study.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo5 {
    public static void main(String[] args) {
        String str = "来黑马程序员学习Java" +
                "电话:18312341234,18512341234" +
                "或者联系邮箱:boniu@itheima.cn" +
                "座机电话01045332222,010-33332222" +
                "邮箱:bozai@itheima.cn" +
                "热线电话:400-618-9090,400-618-4000,4006184000,4006189090";

        String regex1 = "1\\d{10}";
        String regex2 = "\\w+@[\\w&&[^_]]{2,7}(\\.[a-zA-Z]{2,3}){1,2}";
        String regex3 = "010-?\\d{8}";
        String regex4 = "400-?\\d{3}-?\\d{4}";

        Pattern p1 = Pattern.compile(regex1);
        Matcher m1 = p1.matcher(str);
        Pattern p2 = Pattern.compile(regex2);
        Matcher m2 = p2.matcher(str);
        Pattern p3 = Pattern.compile(regex3);
        Matcher m3 = p3.matcher(str);
        Pattern p4 = Pattern.compile(regex4);
        Matcher m4 = p4.matcher(str);

        while (m1.find()) {
            System.out.println(m1.group());
        }
        while (m2.find()) {
            System.out.println(m2.group());
        }
        while (m3.find()) {
            System.out.println(m3.group());
        }
        while (m3.find()) {
            System.out.println(m3.group());
        }
        while (m4.find()) {
            System.out.println(m4.group());
        }
    }
}
