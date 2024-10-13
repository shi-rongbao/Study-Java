package com.stuy.api.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    /*  属性:连接池对线
            保证连接池对线只被实例化一次,因此考虑再static代码块中初始化连接池对象
        方法:对外提供连接的方法
            回收外部传入连接的方法
     */
    private static DataSource dataSource = null;

    static {
        Properties properties = new Properties();
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("drui.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
         return  dataSource.getConnection();
    }

    public static void  freeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
