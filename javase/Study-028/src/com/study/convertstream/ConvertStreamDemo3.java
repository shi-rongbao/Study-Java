package com.study.convertstream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class ConvertStreamDemo3 {
    public static void main(String[] args) throws IOException {
        //获取读取GKB文件对象
        FileReader fr = new FileReader(new File("D:\\Test\\gbkfile.txt"), Charset.forName("GBK"));
        //获取写入UTF-8文件对象
        FileWriter fw = new FileWriter(new File("Study-028\\test1.txt"));
        int ch;
        while ((ch = fr.read()) != -1){
            fw.write(ch);
        }

        fw.close();
        fr.close();
    }
}
