package com.study.string;

import java.util.StringJoiner;

public class StringJoinerDemo1 {
    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        String[] arr = {"1", "2", "3", "4", "5", "6", "7","8", "9"};
        for (int i = 0; i < arr.length; i++) {
            sj.add(arr[i]);
        }
        String str = sj.toString();
        System.out.println(str);

        StringJoiner sj1 = new StringJoiner(",","[","]");
        StringBuilder sb = new StringBuilder();

    }
}
