package com.study.regex;

public class RegexDemo1 {
    public static void main(String[] args) {
        /*
            校验一个QQ号码是否正确
            规则:6位及20位之内,0不能在开头,必须全部是数组
         */
        String qq = "2502260933";

        System.out.println("12341".matches("[1-2]+"));
    }

    public static boolean checkQQ(String qq) {
        int len = qq.length();
        if (len < 6 || len > 20) {
            return false;
        }

        if (qq.startsWith("0")) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            char c = qq.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
