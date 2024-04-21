package com.study.string;

public class StringTest15 {
    public static void main(String[] args) {
        /*
            给你一个字符串s,由若干个单词组成,单词前后用一些空格字符隔开.
            返回字符串中的最后一个单词的长度.
            单词是指仅由字母组成、不包含任何空格字符的最大子字符串

            例如:
                输入: s = "Hello World" 输出 : 5
                解释:最后一个单词是"World", 长度为5

                输入: s = "   fly me to the moon   " 输出: 4
                解释:最后一个单词是"moon" , 长度为4

                输入: s = "luffy is still joyboy" 输出: 6
                解释: 最后一个单词是长度为6的"joyboy"
         */

        String words = "Hello World";
        int count = 0;
        for (int i = words.length(); i > 0; i--) {
            String str = words.substring(i - 2 , i - 1);
            count++;
            if (str.equals(" ")){
                System.out.println(count);
                break;
            }
        }
    }
}
