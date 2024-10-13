package com.stuy.api.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao {
    //封装简化非DQL语句的方法
    /*
        改方法优化了以下的步骤:
            编写带占位符的SQL语句
            获取preparedStatement对象
            占位符的赋值
            发送SQL语句并获取返回值,使用executeUpdate方法,返回int类型的值
            关闭preparedStatement资源

        该方法第一个参数要求传入编写的带占位符的SQL语句,第二个参数是给占位符赋值的参数
     */
    //可变参数可以当做数组使用
    public int executeUpdate(String sql, Object... params) throws SQLException {
        //第一步还是要先获取连接
        Connection connection = JDBCUtilsV2.getConnection();

        //获取preparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //遍历可变参数,占位符赋值
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }

        //发送SQL语句并获取返回值,受影响的行数
        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();
        //如果没有开启事物,则正常关闭connection资源,如果开启了事物,则业务层自己处理
        //开启事物:connection.setAutoCommit(false);
        if (connection.getAutoCommit()) {
            JDBCUtilsV2.freeConnection();
        }
        //将结果返回
        return rows;
    }

    //封装简化DQL语句的方法

    /**
     * 数据库中的每一个表,都可以被封装成Java中一个类
     * 因此将preparedStatement.executeQuery返回的resultSet结果集封装成对象放入List集合中
     *
     * @param clazz  第一个参数确定该方法的泛型,并获取class类对象
     * @param sql    第二个参数是要求传入编写的带占位符的SQL语句
     * @param params 第三个参数是要给占位符赋值的参数
     * @param <T>    待确定的泛型
     * @return 返回值是List集合
     */
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //获取连接
        Connection connection = JDBCUtilsV2.getConnection();

        //获取prepareStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //占位符赋值
        if (params != null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i - 1]);
            }
        }

        ResultSetMetaData metaData = preparedStatement.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<T> list = new ArrayList<>();

        //发送SQL语句并获取结果集
        ResultSet resultSet = preparedStatement.executeQuery();

        //外层while循环遍历行,内层for循环遍历列
        while (resultSet.next()) {
            //调用类的无参构造起
            T t = clazz.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                //获取当前行第i列的值
                Object value = resultSet.getObject(i);
                //获取当前列的名字
                String propertyName = metaData.getColumnLabel(i);

                //使用反射将值赋给t对象
                //这一步将列的名字传入,获取属性的对象
                Field field = clazz.getDeclaredField(propertyName);
                field.setAccessible(true);//将该属性取消private限制
                field.set(t, value);//将获得的value赋值给对象t
            }
            list.add(t);
        }

        //关闭资源
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()){
            JDBCUtilsV2.freeConnection();
        }
        return list;
    }
}

