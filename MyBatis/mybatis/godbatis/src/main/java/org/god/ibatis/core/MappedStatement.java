package org.god.ibatis.core;

/**
 * 普通的java类,POJO,用于封装一个SQL标签
 * 一个MappedStatement对象对应一个SQL标签
 * 一个SQL标签中的所有信息封装到MappedStatement独享当中.
 */
public class MappedStatement {
    // resultType用于指定select语句的封装类型
    private String resultType;

    // sql语句
    private String sql;

    public MappedStatement() {
    }

    public MappedStatement(String resultType, String sql) {
        this.resultType = resultType;
        this.sql = sql;
    }

    /**
     * 获取
     * @return resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * 设置
     * @param resultType
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * 获取
     * @return sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * 设置
     * @param sql
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    public String toString() {
        return "MappedStatement{resultType = " + resultType + ", sql = " + sql + "}";
    }
}
