package com.study.array;

public class Phone {
    private int price;
    private String brand;

    public Phone() {
    }

    public Phone(int price, String brand) {
        this.price = price;
        this.brand = brand;
    }

    /**
     * 获取
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * 获取
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String toString() {
        return "Phone{price = " + price + ", brand = " + brand + "}";
    }
}
