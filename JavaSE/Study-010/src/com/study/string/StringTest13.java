package com.study.string;

import java.util.Random;

public class StringTest13 {
    public static void main(String[] args) {
        /*
            需求:
                生成验证码
                内容:可以是小写字母,也可以是大写字母,还可以是数字
                规则:
                长度为5
                内容中4为字母,1位数字
         */

        // 创建一个长度为52的字符数组，用于存储所有可能的字母（包括大小写）
        char[] arr = new char[52];
        for (int i = 0; i < 26; i++) {
            arr[i] = (char) (97 + i); // 存储小写字母
        }
        for (int i = 26; i < 52; i++) {
            arr[i] = (char) (65 + i - 26); // 存储大写字母
        }
        Random r = new Random();
        String str = "";
        for (int i = 0; i < 4; i++) {
            str = str + arr[r.nextInt(arr.length)]; // 随机选择4个字母并拼接到验证码字符串中
        }
        str = str + r.nextInt(10); // 随机选择一个数字并拼接到验证码字符串的末尾

        char[] chars = str.toCharArray();
        int number = r.nextInt(4);
        char c = chars[chars.length - 1];
        chars[chars.length - 1] = chars[number];
        chars[number] = c;
        str = new String(chars); // 将字符数组转换为字符串
        System.out.println(str); // 打印生成的验证码
    }
}
