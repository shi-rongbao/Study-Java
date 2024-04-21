package com.study.commonsio;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CommonsIODemo1 {
    public static void main(String[] args) throws IOException {
        File src = new File("Study-028\\commonsTest.txt");
        File dest = new File("Study-028\\copyCommonsTest.txt");

        FileUtils.copyFile(src,dest);
    }
}
