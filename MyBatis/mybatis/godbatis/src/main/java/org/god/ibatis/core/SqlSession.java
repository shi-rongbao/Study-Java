package org.god.ibatis.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;

/**
 * 专门负责执行SQL语句的会话对象
 */
public class SqlSession {
    private SqlSessionFactory factory;

    public SqlSession(SqlSessionFactory factory) {
        this.factory = factory;
    }

    /**
     * 执行insert语句,向数据库表中插入记录
     *
     * @param sqlId sql语句的id
     * @param pojo  插入的数据
     * @return 影响数据库表的行数
     */
    public int insert(String sqlId, Object pojo) {
        int rows = 0;
        try {
            // 获取数据库连接对象
            Connection connection = factory.getTransaction().getConnection();
            // 获取到被封装的sql标签中的sql语句
            String godBatisSql = factory.getMappedStatements().get(sqlId).getSql();
            // 将sqlMapper.xml文件中的sql语句转换为JDBC的sql语句
            String sql = godBatisSql.replaceAll("#\\{[0-9a-zA-Z_$]*}", "?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int fromIndex = 0;
            int index = 1;
            while (true) {
                int jingIndex = godBatisSql.indexOf("#", fromIndex);
                if (jingIndex < 0) {
                    break;
                }
                int youKuoHaoIndex = godBatisSql.indexOf("}", fromIndex);
                String propertyName = godBatisSql.substring(jingIndex + 2, youKuoHaoIndex).trim();
                fromIndex = youKuoHaoIndex + 1;
                String getMethodName = "get" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                Method getMethod = pojo.getClass().getDeclaredMethod(getMethodName);
                Object propertyValue = getMethod.invoke(pojo);
                preparedStatement.setString(index, propertyValue.toString());
                index++;
            }

            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * 执行查询语句,查询一个对象,该方法只适合返回一条记录的sql语句
     *
     * @param sqlId sqlID
     * @param param 占位符
     * @return 返回被封装成对象的查询结果
     */
    public Object selectOne(String sqlId, Object param) {
        Object obj = null;
        try {
            // 获取connection对象
            Connection connection = factory.getTransaction().getConnection();
            // 根据sqlID从map集合中获取sql标签对象
            MappedStatement mappedStatement = factory.getMappedStatements().get(sqlId);
            // 获取sql语句(xml文件中的sql语句)
            String godbatisSql = mappedStatement.getSql().trim();
            // 替换为jdbcSQL
            String sql = godbatisSql.replaceAll("#\\{[a-zA-Z0-9_$]*}", "?");
            // prepareStatement对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, param.toString());
            // 获取查询结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            // 获取resultType
            String resultType = mappedStatement.getResultType();

            // 由于只有查询一个对象,因此不采用循环
            if (resultSet.next()) {
                // 反射获取当前类
                Class<?> aClass = Class.forName(resultType);
                // 创建类的对象
                obj = aClass.getDeclaredConstructor().newInstance();

                // 拿到ResultSetMetaData对象
                ResultSetMetaData rsmd = resultSet.getMetaData();
                // 拿到列的数量
                int count = rsmd.getColumnCount();
                // 循环 列 次数
                for (int i = 0; i < count; i++) {
                    // 拿到列名,也就作为属性名
                    String columnName = rsmd.getColumnName(i + 1);
                    // 拼接set方法
                    String setMethodName = "set" + columnName.toUpperCase().charAt(0) + columnName.substring(1);
                    // 根据方法名拿到方法
                    Method setMethod = aClass.getDeclaredMethod(setMethodName, String.class);
                    // 调用set方法,给obj赋值,传入的参数就是列名对应的值
                    setMethod.invoke(obj,resultSet.getString(columnName));
                }

//                // 获取到所有的属性
//                Field[] fields = aClass.getDeclaredFields();
//                // 遍历属性
//                for (Field field : fields) {
//                    // 这里的field是每个属性
//                    // 拿到属性的名字
//                    String fieldName = field.getName();
//                    // 拼接set方法名
//                    String setMethodName = "set" + fieldName.toUpperCase().charAt(0) + fieldName.substring(1);
//                    // 根据方法名拿到方法
//                    Method setMethod = aClass.getDeclaredMethod(setMethodName, String.class);
//                    // 调用set方法,传入参数
//                    String value = resultSet.getString(fieldName);
//                    setMethod.invoke(obj, value);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


    public void commit() {
        factory.getTransaction().commit();
    }

    public void rollback() {
        factory.getTransaction().rollback();
    }

    public void close() {
        factory.getTransaction().close();
    }
}
