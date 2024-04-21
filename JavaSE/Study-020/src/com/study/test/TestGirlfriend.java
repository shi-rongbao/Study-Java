package com.study.test;

import java.util.Arrays;
import java.util.Comparator;

public class TestGirlfriend {
    public static void main(String[] args) {
        Girlfriend gf1 = new Girlfriend("jyj", 8, 130);
        Girlfriend gf2 = new Girlfriend("qkx", 18, 167);
        Girlfriend gf3 = new Girlfriend("zej", 18, 172);
        Girlfriend gf4 = new Girlfriend("lxr", 15, 173);

        Girlfriend[] gfArr = {gf1, gf2, gf3, gf4};

        Arrays.sort(gfArr,
                (Girlfriend o1, Girlfriend o2) -> {
                    if (o2.getAge() == o1.getAge()){
                        if (o2.getHigh() == o1.getHigh()){
                            return o1.getName().compareTo(o2.getName());
                        }
                        return o2.getHigh() - o1.getHigh();
                    }
                    return o2.getAge() - o1.getAge();
                }
        );


        System.out.println("姓名\t年龄\t身高");
        for (int i = 0; i < gfArr.length; i++) {
            System.out.println(gfArr[i]);
        }
    }
}
