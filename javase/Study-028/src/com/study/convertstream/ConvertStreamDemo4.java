package com.study.convertstream;

import java.io.*;

public class ConvertStreamDemo4 {
    public static void main(String[] args) throws IOException {
        //InputStreamReader是字符转换输入流,目的将字节流可以转换成字符流
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Study-028\\csb.txt")));
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
        br.close();
    }
}
