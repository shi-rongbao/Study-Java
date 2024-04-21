package com.study.regex;

public class RegexDemo9 {
    public static void main(String[] args) {
        String regex1 = "(.).+\\1";
        System.out.println("a1dfa".matches(regex1));
        System.out.println("&1d~f&".matches(regex1));
        System.out.println("a1dfb".matches(regex1));

        System.out.println("===============================");

        String regex2 = "(.+).+\\1";
        System.out.println("abc123abc".matches(regex2));
        System.out.println("b456b".matches(regex2));
        System.out.println("123789123".matches(regex2));
        System.out.println("&!@abc&!@".matches(regex2));
        System.out.println("&!@abc&!#".matches(regex2));

        System.out.println("===============================");

        String regex3 = "((.)\\2*).+\\1";
        System.out.println("aaa123aa".matches(regex3));

    }
}
