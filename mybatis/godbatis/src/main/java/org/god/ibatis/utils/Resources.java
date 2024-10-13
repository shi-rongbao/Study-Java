package org.god.ibatis.utils;

import java.io.InputStream;

/**
 * godbatis框架提供的一个工具类.
 * 这个工具类专门完成`类路径`中资源的加载
 *
 */
public class Resources {
    // 私有化构造方法
    private Resources() {}


    /**
     * 从类路径中获取指定资源的输入流
     *
     * @param resource 要获取的资源路径
     * @return 返回指定资源的输入流
     */
    public static InputStream getResourceAsStream(String resource){
        return ClassLoader.getSystemResourceAsStream(resource);
    }

}
