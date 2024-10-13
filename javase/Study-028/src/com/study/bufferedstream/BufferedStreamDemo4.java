package com.study.bufferedstream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedStreamDemo4 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("Study-028\\b.txt"));
        bw.write("今天必须要学完,不能浪费时间啊");
        bw.newLine();
        bw.write("明天不用上课");
        bw.newLine();
        bw.write("我要在下一次web课前");
        bw.newLine();
        bw.write("学习完JavaWeb");
        bw.close();
    }
}
