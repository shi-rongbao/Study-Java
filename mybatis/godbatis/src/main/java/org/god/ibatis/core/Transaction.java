package org.god.ibatis.core;

import java.sql.Connection;

/**
 * 事务管理器接口
 * 所有的事务管理器都应该遵循该规范
 * JDBC事物管理器
 * MANAGED事物管理器
 */
public interface Transaction {
    // 提交事物
    void commit();

    // 回滚事物
    void rollback();

    // 关闭事物
    void close();

    // 开启数据库连接对象
    void openConnection();

    // 获取数据库连接对象
    Connection getConnection();
}

