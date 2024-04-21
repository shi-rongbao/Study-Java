package com.study.regex;

public class RegexDemo8 {
    public static void main(String[] args) {
        String str = "大帅哥djawghbdwbnavjhs3124312小帅哥djkawbndvbawvs674531中帅哥";

        String regex = "\\w+";
        System.out.println(str.replaceAll(regex, "vs"));
        String[] arr = str.split(regex);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
