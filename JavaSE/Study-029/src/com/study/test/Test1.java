package com.study.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
    public static void main(String[] args) throws IOException {
        /*
            制造假数据,从网络上爬取姓名
            获取姓氏:https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0
            获取男生名字:http://www.haoming8.cn/baobao/10881.html
            获取女生名字:http://www.haoming8.cn/baobao/7641.html
         */

        //定义String变量存储网址
        String familyNameNet = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";
        String boyNameNet = "http://www.haoming8.cn/baobao/10881.html";
        String girlNameNet = "http://www.haoming8.cn/baobao/7641.html";

        String familyName = webCrawler(familyNameNet);
        String boyName = webCrawler(boyNameNet);
        String girlName = webCrawler(girlNameNet);

        //System.out.println(familyName);
        //System.out.println(boyName);
        //System.out.println(girlName);

        ArrayList<String> date1 = getDate(familyName, "(.{4})(，|。)", 1);
        ArrayList<String> date2 = getDate(boyName, "([\\u4E00-\\u9FA5]{2})(、|。)", 1);
        ArrayList<String> date3 = getDate(girlName, "((.. ){4}..)", 0);


        //处理数据
        //姓氏数据
        ArrayList<String> familyNameList = new ArrayList<>();
        for (String str : date1) {
            for (int i = 0; i < str.length(); i++) {
                familyNameList.add(str.charAt(i) + "");
            }
        }

        //男生姓名数据,只需要去重处理
        /*
            两种处理方案,
                1: HashSet集合,添加元素自动去重
                2: ArrayList集合,添加元素时先判断,如果存在就不存入,如果不存在则存入
         */
        //这里使用ArrayLis集合
        ArrayList<String> boyNameList = new ArrayList<>();
        for (String str : date2) {
            //判断集合中是否已经存在str,如果存在,则不添加
            if (!boyNameList.contains(str)) {
                boyNameList.add(str);
            }
        }

        //女生姓名数据,每五个数据为一个元素,将数据分开存放
        ArrayList<String> girlNameList = new ArrayList<>();
        for (String str : date3) {
            //将女生数据用" "分开放入数组中
            //循环遍历数组,将数组的元素添加到集合中
            String[] s = str.split(" ");
            for (int i = 0; i < s.length; i++) {
                girlNameList.add(s[i]);
            }
        }

        ArrayList<String> list = getInfos(familyNameList, boyNameList, girlNameList, 70, 70);
        //打乱集合
        Collections.shuffle(list);


        //将生成的姓名写入本地文件中
        BufferedWriter bw = new BufferedWriter(new FileWriter("Study-029\\names.txt"));
        for (String info : list) {
            bw.write(info);
            bw.newLine();
        }
        bw.close();
    }

    //定义方法,生成假数据
    public static ArrayList<String> getInfos(ArrayList<String> familyNameList, ArrayList<String> boyNameList, ArrayList<String> girlNameList, int boyCount, int girlCount) {
        //需要确保生成的名字唯一,因此使用HashSet存储姓名
        HashSet<String> boyHs = new HashSet<>();
        while (true) {
            //当集合的长度等于要生成男生的姓名个数时,停止循环
            if (boyHs.size() == boyCount) {
                break;
            }

            //打乱集合中的元素
            Collections.shuffle(familyNameList);
            Collections.shuffle(boyNameList);
            boyHs.add(familyNameList.get(0) + boyNameList.get(0));
        }

        HashSet<String> girlHs = new HashSet<>();
        while (true) {
            if (girlHs.size() == girlCount) {
                break;
            }
            //打乱集合中的元素
            Collections.shuffle(familyNameList);
            Collections.shuffle(girlNameList);
            girlHs.add(familyNameList.get(0) + girlNameList.get(0));
        }

        //创建ArrayList集合,用来存放最终的数据
        ArrayList<String> info = new ArrayList<>();
        //遍历男生姓名集合
        //boyName表述集合中每个男生的姓名
        for (String boyName : boyHs) {
            //创建表示年龄的随机数
            Random r = new Random();
            int age = r.nextInt(10) + 18;
            //将信息添加到集合中
            info.add(boyName + "-男-" + age);
        }

        //girlName表述集合中每个男生的姓名
        for (String girlName : girlHs) {
            //创建表示年龄的随机数
            Random r = new Random();
            int age = r.nextInt(8) + 16;
            //将信息添加到集合中
            info.add(girlName + "-女-" + age);
        }
        return info;
    }


    //定义方法,获取我想要的数据
    public static ArrayList<String> getDate(String str, String regex, int index) {
        ArrayList<String> list = new ArrayList<>();
        //获取正则表达式对象
        Pattern pattern = Pattern.compile(regex);
        //按照pattern的规则,到str中获取数据
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            list.add(matcher.group(index));
        }
        return list;
    }

    //定义方法,从网络中爬取数据,并将数据拼接成字符串返回
    //形参:网址
    //返回值:爬取到的所有数据
    public static String webCrawler(String net) throws IOException {
        StringBuilder sb = new StringBuilder();

        //创建URL对象
        URL url = new URL(net);

        //连接上这个网址
        URLConnection conn = url.openConnection();

        //由于网站中有中文内容,因此字节输入流不能满足需求,使用转换流,将字节流转变为字符流
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());

        int ch;
        while ((ch = isr.read()) != -1) {
            sb.append((char) ch);
        }

        return sb.toString();
    }
}
