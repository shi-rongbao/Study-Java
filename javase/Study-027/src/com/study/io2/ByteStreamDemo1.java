package com.study.io2;

import java.io.FileInputStream;
import java.io.IOException;

public class ByteStreamDemo1 {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("D:\\StudyJava\\JavaSE\\Study-001\\Study-027\\src\\com\\study\\io2\\aaa.txt");
        int b1 = fis.read();
        int b2 = fis.read();
        fis.close();
        System.out.println((char)b1);
        System.out.println(b2);
    }
}
