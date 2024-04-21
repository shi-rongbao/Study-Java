package com.study.test2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Test5 {
    public static void main(String[] args) throws IOException {
        /*
            Txt文件中事先准备好一些学生信息,每个学生的信息独占一行
            要求:
                每次被点到的学生,再次被点到的概率在原先的基础上降低一半

            提示: 本题的核心就是带权重的随机
         */

        //从文件中读取数据
        //创建集合,从文件中读取数据并封装成学生对象,存入集合中
        ArrayList<Student> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("Study-029\\names.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("-");
            Student stu = new Student(arr[0], arr[1], Integer.parseInt(arr[2]), Double.parseDouble(arr[3]));
            list.add(stu);
        }
        br.close();

        //计算权重的总和
        double weight = 0;
        for (Student stu : list) {
            weight += stu.getWeight();
        }

        //计算每一个人的实际占比
        double[] arr = new double[list.size()];
        int index = 0;
        for (Student stu : list) {
            arr[index] = stu.getWeight() / weight;
            index++;
        }

        //计算每一个人的权重占比范围
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i] + arr[i - 1];
        }

        //获取一个0.0~1.0之间的随机数,使用math类
        double number = Math.random();

        //Arrays.binarySearch使用二分查找方法返回 - 插入点 - 1
        //给结果添加-号再-1得到最终的结果
        int result = -Arrays.binarySearch(arr, number) - 1;

        //从集合中获取学生信息
        Student student = list.get(result);
        System.out.println(student);

        //修改该学生的权重
        student.setWeight(student.getWeight() / 2);

        //最后将集合中的学生对象再次写入到文件中
        BufferedWriter bw = new BufferedWriter(new FileWriter("Study-029\\names.txt"));
        for (Student stu : list) {
            bw.write(stu.toString());
            bw.newLine();
        }
        bw.close();
    }
}
