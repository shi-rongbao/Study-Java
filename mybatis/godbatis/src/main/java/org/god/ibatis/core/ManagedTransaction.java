package org.god.ibatis.core;


import java.sql.Connection;

/**
 * Managed事物管理器(godbatis暂时不对此事物管理器进行实现)
 */
public class ManagedTransaction implements Transaction{
    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }

    @Override
    public void openConnection() {

    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
