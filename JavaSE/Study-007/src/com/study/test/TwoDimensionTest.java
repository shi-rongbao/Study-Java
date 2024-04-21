package com.study.test;

public class TwoDimensionTest {
    public static void main(String[] args) {
        /*
        需求:
        某商城每个季度的营业额如下:单位(万元)
        第一季度:22, 66, 44
        第二季度:77, 33, 88
        第三季度:25, 45, 65
        第四季度:11, 66, 99

        要求计算出每个季度的总营业额和全年的总营业额
         */

        // 定义一个二维数组存储每个季度的营业额
        int[][] yearArrArr = {
                {22, 66, 44},
                {77, 33, 88},
                {25, 45, 65},
                {11, 66, 99}
        };

        int sumYear = 0; // 存储全年的总营业额

        // 遍历二维数组，计算每个季度的总营业额和全年的总营业额
        for (int i = 0; i < yearArrArr.length; i++) {
            int[] quarterArr = yearArrArr[i]; // 获取当前季度的营业额数组
            int sum = getSum(quarterArr); // 调用getSum方法计算当前季度的总营业额
            sumYear += sum; // 累加当前季度的总营业额到全年总营业额
            System.out.println("第" + (i + 1) + "季度的营业额为:" + sum + "万元");
        }

        System.out.println("全年的总营业额为:" + sumYear + "万元");
    }

    // 计算一个一维数组的总和
    public static int getSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
