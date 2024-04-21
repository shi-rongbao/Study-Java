package com.study.test2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Test4 {
    public static void main(String[] args) throws IOException {
        /*
            需求:
                一个文件里面存储了班级同学的姓名,每一个姓名占一行.
                要求通过程序实现随机点名器.

            运行效果:
                被点到的学生不会再被点到
                如果班级中所有的学生都点完了,需要自动的重新开启第二轮点名
                细节1:假设班级有10个学生,每一轮中每一位学生只能被点到一次,程序运行10次,第一轮结束
                细节2:第11次运行的时候,我们不需要手动操作本地文件,要求程序自动开始第二轮点名
         */

        /*

         */

        //创建文件源和备份文件地址的字符串值
        String src = "Study-029\\names.txt";
        String copyFile = "Study-029\\copyNames.txt";
        //先将文件中的数据读取到集合中
        ArrayList<String> list = readFile(src);

        //判断集合中是否还有数据,先考虑集合中有数据的情况,反过来在考虑集合中没有数据
        if (list.size() == 0) {
            //集合中没有数据了
            //重新开始
            //读取备份文件数据放入list集合中
            list = readFile(copyFile);
            //将集合中的内容考入到源文件
            writeFile(src,list,false);
            //删除备份文件
            new File(copyFile).delete();

        }
        //集合中还有数据
        //1.打乱集合
        Collections.shuffle(list);
        //2.获取第一个元素并移除
        String nameInfo = list.get(0);
        list.remove(0);
        //打印随机到的学生信息
        System.out.println("当前被点到的学生为：" + nameInfo);
        //将删除元素后的集合写到源文件中,源文件清空(也就是不追加)
        writeFile(src, list, false);
        //将删除的元素备份,不清空备份文件
        writeFile(copyFile, nameInfo, true);

    }

    private static void writeFile(String pathFile, String str, boolean append) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile));
        bw.write(str);
        bw.newLine();
        bw.close();
    }

    private static void writeFile(String pathFile, ArrayList<String> list, boolean isAppend) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile));
        for (String name : list) {
            bw.write(name);
            bw.newLine();
        }
        bw.close();
    }

    //将文件内学生姓名读取到集合中
    public static ArrayList<String> readFile(String pathFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(pathFile));
        ArrayList<String> list = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            list.add(line.split("-")[0]);
        }
        br.close();
        return list;
    }

    private static void extracted(String name) throws IOException {
        //将学生写入copyName文件
        BufferedWriter bw = new BufferedWriter(new FileWriter("Study-029\\copyName.txt"));

        bw.write(name);
        bw.newLine();
        bw.close();
    }
}
