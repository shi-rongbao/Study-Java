package com.study.regex;

public class RegexDemo3 {
    public static void main(String[] args) {
        /*
            需求:
                请编写正则表达式验证是否满足要求
                    大小写字母,数字,下划线一共4-16位
                    简单要求:18位身份证号,前17位任意数字,最后一位可以是大写或者小写的x
                    复杂要求:按照身份证号码的格式严格要求.
         */
        String regex1 = "\\w{4,16}";
        String regex2 = "[1-9]\\d{16}\\d|x|X";
        //"341302 20041019 7219"
        String regex3 = "[1-9]\\d{5}(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[12])[1-9]\\d{2}[\\dxX]";
        String regex4 = "[1-9]\\d{5}";
        System.out.println("34130220041019721X".matches(regex3));
    }
}
