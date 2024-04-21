package com.stuy.api.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUsePart {
    //硬编码
    //直接使用代码设置连接池连接参数方式
    public void testHard() throws SQLException {
        //创建连接池对象
        DruidDataSource dds = new DruidDataSource();
        //设置连接池参数
        //必须的参数: 注册驱动, url user password
        //非必须的参数: 连接池数量,最大的连接数量
        dds.setUrl("jdbc:mysql://127.0.0.1:3306/atguigu");
        dds.setUsername("root");
        dds.setPassword("123456");
        dds.setDriverClassName("com.mysql.cj.jdbc.Driver");

        //获取链接(通用)
        DruidPooledConnection connection = dds.getConnection();

        //回收链接(通用)
        connection.close();
    }

    //软编码
    //使用配置文件设置连接池参数
    public void testSoft() throws Exception {
        //通过读取外部配置文件的方法,实例化druid连接池对象

        //读取外部配置文件 Properties

        Properties properties = new Properties();

        //读取src下的文件,可以使用类加载器提供的方法
        InputStream is = DruidUsePart.class.getClassLoader().getResourceAsStream("druid.properties");

        properties.load(is);

        //创建连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();

        connection.close();
    }
}
