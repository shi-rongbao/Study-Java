package com.study.string;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String s = new String();
        System.out.println("@" + s + "@");

        String s1 = new String("abc");
        System.out.println(s1);

        char[] chs = {'a', 'b', 'c', 'd', 'e'};
        String s2 = new String(chs);
        System.out.println(s2);

        byte[] bytes = {97, 98, 99, 100};
        String s3 = new String(bytes);
        System.out.println(s3);

        Scanner sc = new Scanner(System.in);
        String s4 = sc.next();
        String s5 = "abc";
        System.out.println(s4 == s5);
    }
}
