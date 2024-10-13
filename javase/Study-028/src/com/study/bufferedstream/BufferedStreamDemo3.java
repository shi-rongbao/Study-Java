package com.study.bufferedstream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedStreamDemo3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Study-028\\a.txt"));
        String line;
        //如果从内存中读取不到东西,则返回null
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
        br.close();
    }
}
