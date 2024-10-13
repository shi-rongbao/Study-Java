package com.shirongbao.client;

import com.shirongbao.annotation.Component;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ComponentScan {
    public static void main(String[] args) {
        // 这是包名
        String packageName = "com.shirongbao.bean";
        // 将包名中的 . 换为 /
        String packagePath = packageName.replaceAll("\\.", "/");
        // 拿到bean目录在磁盘中的真实路径
        URL url = ClassLoader.getSystemClassLoader().getResource(packagePath);
        // 拿到路径
        String path = url.getPath();

        File file = new File(path);
        // 获取这个文件的所有子文件
        File[] files = file.listFiles();
        // 创建map集合
        Map<String, Object> beanMap = new HashMap<>();
        // 将文件数组转为stream流并遍历
        Arrays.stream(files).forEach(f -> {
            try {
                // 拼接全类名
                String className = packageName + "." + f.getName().split("\\.")[0];
                // 获取当前类
                Class<?> aClass = Class.forName(className);
                // 如果类有Component注解那么就获取注解的value并new当前类的对象同时放入map集合中
                if (aClass.isAnnotationPresent(Component.class)) {
                    beanMap.put(aClass.getAnnotation(Component.class).value(), aClass.getDeclaredConstructor().newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Set<Map.Entry<String, Object>> entries = beanMap.entrySet();
        entries.forEach(System.out::println);
    }
}
