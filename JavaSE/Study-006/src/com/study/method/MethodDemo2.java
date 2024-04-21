package com.study.method;

public class MethodDemo2 {
    public static void main(String[] args) {
        double area1 = getArea(5.3, 7.3);
        double area2 = getArea(5.8, 2.3);
        System.out.println(Math.max(area1, area2));
    }

    public static double getArea(double len, double width) {
        double area = len * width;
        return area;
    }
}
