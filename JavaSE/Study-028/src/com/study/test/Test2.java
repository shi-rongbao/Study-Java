package com.study.test;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Study-028\\csb.txt"));
        ArrayList<String> list = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null){
            list.add(line);
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (Integer.parseInt(o1.split("\\.")[0]) - Integer.parseInt(o2.split("\\.")[0]));
            }
        });


        BufferedWriter bw = new BufferedWriter(new FileWriter("Study-028\\csb1.txt"));
        for (String s : list) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();
        br.close();
    }
}

