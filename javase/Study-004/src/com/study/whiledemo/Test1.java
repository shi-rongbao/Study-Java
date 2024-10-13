package com.study.whiledemo;

public class Test1 {
    public static void main(String[] args) {
        /*
            需求:0.1毫米厚的纸,对折多少次高度为珠峰高度(8844.43米=8844430毫米)
         */
        double paper = 0.1;
        double peakHeight = 8844430;
        int count = 0;
        while (paper <= peakHeight){
            paper *= 2;
            count++;
        }
        System.out.println(count);
    }
}
