package com.study.test;

import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        /*
            需求:
                在唱歌比赛中,有6名评委给选手打分,分数是[0-100]之间的整数.选手最终得分为:
                去掉一个最高分去掉一个最低分后4个评委评分的平均分,请完成上述过程并计算出选手的得分.
         */
        Scanner sc = new Scanner(System.in);
        int[] scores = new int[6];

        for (int i = 0; i < 6; i++) {
            System.out.println("请输入第" + (i + 1) + "名评委的打分:");
            int score = sc.nextInt();
            if (score >= 0 && score <= 100) {
                scores[i] = score;
            } else {
                System.out.println("输入有误,请重新输入!");
                i--;
            }
        }

        System.out.println(getAvg(scores));
    }

    public static int getMax(int[] score) {
        int max = score[0];
        for (int i = 1; i < score.length; i++) {
            if (score[i] > max) {
                max = score[i];
            }
        }
        return max;
    }

    public static int getMin(int[] score) {
        int min = score[0];
        for (int i = 1; i < score.length; i++) {
            if (score[i] < min) {
                min = score[i];
            }
        }
        return min;
    }

    public static int getAvg(int[] score) {
        int sum = 0;
        for (int i = 0; i < score.length; i++) {
            sum += score[i];
        }
        int max = getMax(score);
        int min = getMin(score);
        sum = sum - max - min;
        int avg = sum / (score.length - 2);
        return avg;
    }
}
