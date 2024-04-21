package com.study.practice;

public class GirlFriendTest {
    public static void main(String[] args) {
        GirlFriend[] arr = new GirlFriend[4];

        GirlFriend g1 = new GirlFriend("第一个女朋友", 19, '女', "时荣宝");
        GirlFriend g2 = new GirlFriend("第二个女朋友", 15, '女', "时荣宝");
        GirlFriend g3 = new GirlFriend("第三个女朋友", 23, '女', "时荣宝");
        GirlFriend g4 = new GirlFriend("第四个女朋友", 18, '女', "时荣宝");

        arr[0] = g1;
        arr[1] = g2;
        arr[2] = g3;
        arr[3] = g4;

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i].getAge();
        }
        double ave = sum * 1.0 / arr.length;
        System.out.println(ave);

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getAge() < ave) {
                System.out.println(arr[i].toString());
                count++;
            }
        }
        System.out.println("这样的妹子有" + count + "个");
    }
}
