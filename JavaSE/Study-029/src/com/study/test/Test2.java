package com.study.test;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        //定义String变量存储网址
        String familyNameNet = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";
        String boyNameNet = "http://www.haoming8.cn/baobao/10881.html";
        String girlNameNet = "http://www.haoming8.cn/baobao/7641.html";
        //获取网站中所有数据并且拼接成字符串
        String strTemp1 = HttpUtil.get(familyNameNet);
        String strTemp2 = HttpUtil.get(boyNameNet);
        String strTemp3 = HttpUtil.get(girlNameNet);

        //通过正则表达式筛选出需要的数据
        List<String> date1 = ReUtil.findAll("(.{4})(，|。)", strTemp1, 1);
        List<String> date2 = ReUtil.findAll("([\\u4E00-\\u9FA5]{2})(、|。)", strTemp2, 1);
        List<String> date3 = ReUtil.findAll("([\\u4E00-\\u9FA5]{2} ){4}[\\u4E00-\\u9FA5]{2}", strTemp3, 0);

        //处理数据
        ArrayList<String> familyNameList = new ArrayList<>();
        for (String str : date1) {
            for (int i = 0; i < str.length(); i++) {
                familyNameList.add(str.charAt(i) + "");
            }
        }

        ArrayList<String> boyNameList = new ArrayList<>();
        for (String str : date2) {
            if (!boyNameList.contains(str)){
                boyNameList.add(str);
            }
        }

        ArrayList<String> girlNameList = new ArrayList<>();
        for (String str : date3) {

            String[] arr = str.split(" ");
            for (int i = 0; i < arr.length; i++) {
                girlNameList.add(arr[i]);
            }
        }

    }
}
