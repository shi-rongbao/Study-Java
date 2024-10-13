package com.atguigu.cache;

/**
 * @author ShiRongbao
 * @create 2024/6/14 23:04
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        String a = new String("43");
        String b = new String("43");
        System.out.println(a == b);
        System.out.println(a.equals(b));
        // intern方法从常量池中拿到数据
        System.out.println(a.intern() == b.intern());

        System.out.println("=========================");

        String c = "lock" + "23";
        String d = "lock" + "23";
        System.out.println(c == d);
        System.out.println(c.equals(d));
    }
}
