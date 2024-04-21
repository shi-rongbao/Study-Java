package com.study.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class ReflectTest {
    public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalAccessException {
        Teacher t = new Teacher("波妞", 10000);
        Student s = new Student("小A", 23, '女', 167.5, "睡觉");

        saveObject(s);

    }

    public static void saveObject(Object obj) throws IOException, IllegalAccessException {
        //创建文件写入流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter("Study-032\\name.txt"));

        //获取字节码文件对象
        Class clazz = obj.getClass();
        //获取类里的所有属性
        Field[] fields = clazz.getDeclaredFields();
        //遍历数组
        for (Field field : fields) {
            //将私有属性设置为暂时可以访问
            field.setAccessible(true);
            //写入数据
            bw.write(field.getName() + "=" + field.get(obj));
            //换行
            bw.newLine();
        }
        //释放资源,关流
        bw.close();


    }
}
