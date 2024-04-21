package com.study.test;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Study-028\\csb.txt"));

        String line;
        TreeMap<Integer,String> tm = new TreeMap<>();
        while ((line = br.readLine()) != null){
            String[] str = line.split("\\.");
            tm.put(Integer.parseInt(str[0]),line);
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter("Study-028\\csb2.txt"));
        Set<Map.Entry<Integer, String>> entries = tm.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            bw.write(entry.getValue());
            bw.newLine();
        }
        bw.close();
    }
}
