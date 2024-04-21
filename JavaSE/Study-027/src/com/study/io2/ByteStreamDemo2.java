package com.study.io2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteStreamDemo2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("D:\\StudyJava\\JavaSE\\Study-001\\Study-027\\src\\com\\study\\io2\\aaa.txt");

        int b;
        while ((b = fis.read()) != -1){
            System.out.print((char)b);
        }
        fis.close();
    }
}
