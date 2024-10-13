package com.study.io2;

import java.io.FileWriter;
import java.io.IOException;

public class CharStreamDemo3 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("D:\\StudyJava\\JavaSE\\Study-001\\Study-027\\src\\com\\study\\io2\\aaa.txt",true);

        fw.write("fuck my roommate 操!");

        fw.close();
    }
}
