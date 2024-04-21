package com.study.io2;

import java.io.FileReader;
import java.io.IOException;

public class CharStreamDemo1 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("D:\\StudyJava\\JavaSE\\Study-001\\Study-027\\src\\com\\study\\io2\\aaa.txt");

        int ch;
        while ((ch = fr.read()) != -1){
            System.out.print(ch);
        }

        fr.close();
    }
}
