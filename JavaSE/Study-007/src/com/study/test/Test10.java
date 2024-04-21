package com.study.test;

import java.util.Random;
import java.util.Scanner;

public class Test10 {
    public static void main(String[] args) {
        int[] arr = creatNumber();
        int[] userInputNumber = userInputNumber();
        int redCount = 0;
        int blueCount = 0;
        for (int i = 0; i < userInputNumber.length - 1; i++) {
            int redNumber = userInputNumber[i];
            for (int i1 = 0; i1 < arr.length - 1; i1++) {
                if (redNumber == arr[i1]) {
                    redCount++;
                    break;
                }
            }

        }
        int blueNumber = userInputNumber[userInputNumber.length - 1];
        if (blueNumber == arr[arr.length - 1]) {
            blueCount++;
        }
        System.out.println(redCount);
        System.out.println(blueCount);
        if (blueCount == 1){
            if (redCount == 6){
                System.out.println("一等奖");
            }else if (redCount == 4){
                System.out.println("三等奖");
            }else {
                System.out.println("六等奖");
            }
        }else {
            if (redCount == 6){
                System.out.println("二等奖");
            }else if (redCount == 5){
                System.out.println("四等奖");
            }else if (redCount == 4){
                System.out.println("三等奖");
            }else {
                System.out.println("没中奖");
            }
        }
    }

    public static int[] creatNumber() {
        int[] arr = new int[7];
        Random r = new Random();
        for (int i = 0; i < 6; ) {
            int redNumber = r.nextInt(33) + 1;
            boolean flag = contains(arr, redNumber);
            if (!flag) {
                arr[i] = redNumber;
                i++;
            }
        }

        int blueNumber = r.nextInt(16) + 1;
        arr[arr.length - 1] = blueNumber;

        return arr;

    }

    public static boolean contains(int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                return true;
            }
        }
        return false;
    }

    public static int[] userInputNumber() {
        int[] arr = new int[7];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 6; ) {
            System.out.println("请输入第" + (i + 1) + "个红球号码:");
            int redNumber = sc.nextInt();
            if (redNumber >= 1 && redNumber <= 33) {
                boolean flag = contains(arr, redNumber);
                if (!flag) {
                    arr[i] = redNumber;
                    i++;
                } else {
                    System.out.println("当前号码已经存在");
                }
            } else {
                System.out.println("当前号码超出范围");
            }
        }
        while (true) {
            System.out.println("请输入蓝球号码:");
            int blueNumber = sc.nextInt();
            if (blueNumber >= 1 && blueNumber <= 16) {
                arr[arr.length - 1] = blueNumber;
                break;
            } else {
                System.out.println("输入有误,重新输入!");
            }
        }
        return arr;
    }
}
