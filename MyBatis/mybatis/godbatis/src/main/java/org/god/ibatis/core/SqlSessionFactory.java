package org.god.ibatis.core;

import java.util.Map;

public class SqlSessionFactory {
    // 首先考虑SqlSessionFactory的属性有哪些
    // 解析config.xml文件时要拿到哪些数据
    // build方法要new一个SqlSessionFactory


    /**
     * 事务管理器属性
     * 事务管理器是可以灵活切换的
     * 因此SqlSessionFactory类中的事务管理器应该是面向接口编程的.
     */
    private Transaction transaction;


    // 存放sql语句的Map集合.key是sqlId,value是对应的SQL标签信息对象
    private Map<String, MappedStatement> mappedStatements;

    public SqlSessionFactory() {
    }

    public SqlSessionFactory(Transaction transaction, Map<String, MappedStatement> mappedStatements) {
        this.transaction = transaction;
        this.mappedStatements = mappedStatements;
    }

    /**
     * 获取
     * @return transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * 设置
     * @param transaction
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * 获取
     * @return mappedStatements
     */
    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    /**
     * 设置
     * @param mappedStatements
     */
    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }


    /**
     * 获取sql会话对象
     * @return sql会话对象
     */
    public SqlSession openSession(){
        // 开启会话的前提是已经开启连接
        transaction.openConnection();
        /**
         * 这里应当给sqlSession传入两个参数
         * 第一个参数:   transaction,用于sqlSession调用getConnection()方法获取Connection对象
         * 第二个参数:   map集合(装有sqlID与sql标签对象的集合)
         * 这两个参数也就是sqlSessionFactory对象的参数,因此可以传入this
         */
        return new SqlSession(this);
    }
}
