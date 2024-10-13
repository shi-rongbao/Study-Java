package com.stuy.api.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCCrudPart {
    public void insert() throws SQLException {
        Connection connection = JDBCUtils.getConnection();

        String sql = "INSERT INTO t_user(account, password, nickname) VALUES = (?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1,"ergouzi");
        preparedStatement.setObject(2,"123");
        preparedStatement.setObject(3,"法克鱿马泽");

        int i = preparedStatement.executeUpdate();


        preparedStatement.close();
        JDBCUtils.freeConnection(connection);
    }
}
