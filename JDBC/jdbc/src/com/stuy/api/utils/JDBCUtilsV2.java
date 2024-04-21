package com.stuy.api.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtilsV2 {
    private static DataSource dataSource = null;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

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
        Connection connection = tl.get();
        if (connection == null){
            connection = dataSource.getConnection();
            tl.set(connection);
        }
        return connection;
    }

    public static void freeConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection != null){
            tl.remove();
            connection.setAutoCommit(true);
            connection.close();
        }
    }
}
