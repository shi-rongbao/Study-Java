package com.study.mid;

public class Solution_238 {
    public static void main(String[] args) {
        /*
            给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
            题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
            请不要使用除法，且在 O(n) 时间复杂度内完成此题。
         */
    }

    //此结果时间复杂度为O(n^2)  不符合要求
    public static int[] productExceptSelf(int[] nums) {
        int length = nums.length;//获取数组长度
        int[] answer = new int[nums.length];//创建新的数组,长度等于老数组的长度
        for (int i = 0; i < answer.length; i++) {
            int temp = 1;//定义临时变量等于1
            for (int j = 0; j < nums.length; j++) {
                if (j != i) {//判断索引是否相等
                    temp *= nums[j];//不相等则做乘法
                }//循环,将i之外其余各元素相乘
            }
            answer[i] = temp;//记录最终的临时变量的值
        }
        return answer;
    }
}
