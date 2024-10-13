package com.study.convertstream;

import java.io.*;
import java.nio.charset.Charset;

public class ConvertStreamDemo2 {
    public static void main(String[] args) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("Study-028\\text11.txt"),"GBK");

        osw.write("fuck");
        osw.close();


        FileWriter fw = new FileWriter(new File("Study-028\\text11.txt"), Charset.forName("GBK"));
        fw.write("fuck");
        fw.write("\r\n");
        fw.write("my 你好");
        fw.close();
    }
}
