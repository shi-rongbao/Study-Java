package com.study.mid;

public class Solution_560 {
    public static void main(String[] args) {
        /*
            给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
            例如:
                输入：nums = [1,1,1], k = 2
                输出：2    [1,1] [1,1]

                输入：nums = [1,2,3], k = 3
                输出：2    [1,2]   [3]

                输入：nums = [1, -1, 0], k = 0
                输出：3    [1,-1]   [0]    [1,-1,0]
         */
        int[] nums = {1, -1, 0};
        int k = 0;
        int result = subarraySum(nums, k);
        System.out.println(result);
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                count++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                count++;
                break;
            }
        }
        return count;
    }
}
