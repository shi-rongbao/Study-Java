package com.study.util;

import java.util.ArrayList;

public class StudentUtil {
    private StudentUtil(){}

    public static int getMaxAgeOfStudent(ArrayList<Student> list){
        int maxAge = list.get(0).getAge();
        for (int i = 1; i < list.size(); i++) {
            int ageTemp = list.get(i).getAge();
            if (ageTemp >maxAge){
                maxAge = ageTemp;
            }
        }
        return maxAge;
    }
}
