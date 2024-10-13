package com.study.practice;

public class GoodsTest {
    public static void main(String[] args) {
        Goods[] arr = new Goods[3];

        Goods g1 = new Goods("001", "华为p60", 4499.00, 123);
        Goods g2 = new Goods("002", "电脑", 12999.00, 325);
        Goods g3 = new Goods("003", "保温杯", 299.00, 1654);

        arr[0] = g1;
        arr[1] = g2;
        arr[2] = g3;

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].getId() + " " + arr[i].getName() + " " + arr[i].getPrice() + " " + arr[i].getNumber());
        }

    }
}
