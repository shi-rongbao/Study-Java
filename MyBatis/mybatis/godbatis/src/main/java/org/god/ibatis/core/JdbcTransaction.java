package org.god.ibatis.core;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC事物管理器(godbatis只对此事物管理器进行实现)
 */
public class JdbcTransaction implements Transaction {
    private DataSource dataSource;

    /**
     * 自动提交标志
     * true表示自动提交
     * false表示不用自动提交
     */
    private boolean autoCommit;

    /**
     * 创建事物管理器对象
     *
     * @param dataSource 具体的数据源
     * @param autoCommit 是否自动提交
     */
    public JdbcTransaction(DataSource dataSource, boolean autoCommit) {
        this.dataSource = dataSource;
        this.autoCommit = autoCommit;
    }

    private Connection connection;

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openConnection() {
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                // 开启事务
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
