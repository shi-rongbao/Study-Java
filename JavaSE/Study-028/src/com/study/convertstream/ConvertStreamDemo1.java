package com.study.convertstream;

import java.io.*;
import java.nio.charset.Charset;

public class ConvertStreamDemo1 {
    public static void main(String[] args) throws IOException {
        /*InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\Test\\gbkfile.txt"),"GBK");
        int ch;
        while ((ch = isr.read()) != -1){
            System.out.print((char) ch);
        }
        isr.close();*/

        FileReader fr = new FileReader(new File("D:\\Test\\gbkfile.txt"), Charset.forName("GBK"));
        int ch;
        while ((ch = fr.read()) != -1){
            System.out.print((char) ch);
        }
        fr.close();
    }
}
