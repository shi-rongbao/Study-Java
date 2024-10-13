package com.study.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileTest5 {
    public static void main(String[] args) {
        /*
            需求:
                统计一个文件夹中没中文件的个数并打印.(考虑子文件夹)
                打印格式如下:
                txt:3个
                doc:4个
                jpg:6个
         */


        File file = new File("D:\\StudyJava");

        HashMap<String, Integer> hm = method(file);
        System.out.println(hm);
    }

    public static HashMap<String, Integer> method(File src) {
        //创建map集合用来计数
        HashMap<String, Integer> hm = new HashMap<>();
        //先进入src目录,将src目录下的内容全部放入File数组中
        File[] files = src.listFiles();
        //遍历files数组
        for (File file : files) {
            //如果该元素是文件
            if (file.isFile()) {
                //获取文件名后缀
                String name = file.getName();
                //通过"."来切割,获取文件名的不同部分的数组,要使用转义字符
                String[] arr = name.split("\\.");
                //如果被"."切割的部分的长度大于等于2
                if (arr.length >= 2) {
                    //获取到文件名的最后一个部分,也就是后缀名
                    String endName = arr[arr.length - 1];
                    //如果在集合中已经有这个后缀名的key了
                    if (hm.containsKey(endName)) {
                        //获取到key的value,并自增一次
                        int value = hm.get(endName);
                        value++;
                        //最终将endName和value都放入map集合中
                        hm.put(endName, value);
                    } else {
                        //如果没有,则将endName与1放入集合中
                        hm.put(endName, 1);
                    }
                }
            } else {
                //如果该元素不是文件,则递归
                //获取子文件的map集合
                HashMap<String, Integer> sonMap = method(file);
                //获取子文件集合的entries单列集合
                Set<Map.Entry<String, Integer>> entries = sonMap.entrySet();
                //遍历单列集合
                for (Map.Entry<String, Integer> entry : entries) {
                    //获取子文件集合的key和value
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    //如果老集合中已经存在这个key
                    if (hm.containsKey(key)) {
                        //将新老集合中的value累加放入到老集合中
                        hm.put(key, (value + hm.get(key)));
                    }else {
                        //如果老集合中没有新集合中的key
                        //直接将新集合中的key和value添加到老集合中
                        hm.put(key, value);
                    }
                }
            }
        }
        return hm;
    }
}
