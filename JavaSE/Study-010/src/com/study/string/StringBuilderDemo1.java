package com.study.string;

public class StringBuilderDemo1 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("fuck");
        sb.append("you");
        sb.append(1);
        sb.reverse();
        System.out.println(sb);
        String str = sb.toString();
        System.out.println(str);
    }
}
