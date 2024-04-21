package com.study.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo4 {
    public static void main(String[] args) {
        String str = "Java自从95年问世以来,经历了很多版本,目前企业中用的最多的是Java8和Java11." +
                "因为这两个是长期支持版本,下一个长期支持版本是Java17,相信在未来不久Java17也会柱间登上历史舞台";

        Pattern p = Pattern.compile("Java\\d{0,2}");

        Matcher m = p.matcher(str);

        while (m.find()){
            String s = m.group();
            System.out.println(s);
        }
    }
}
