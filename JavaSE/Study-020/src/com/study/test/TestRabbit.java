package com.study.test;

public class TestRabbit {
    public static void main(String[] args) {
        /*
            有一个很有名的数学逻辑题叫做不死神兔问题,有一对兔子,从出生后第三个月起每个月都生一对兔子,
            小兔子长到第三个月又生一对兔子,如果兔子都不会死,问第十二个月的兔子对数为多少?


            符合菲波那切数列
         */

        int[] arr = new int[12];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println(getRabbit(12));
    }

    public static int getRabbit(int number) {
        if (number == 1 || number == 2) {
            return 1;
        }
        return number = getRabbit(number - 1) + getRabbit(number - 2);
    }
}
