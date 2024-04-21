package com.shirongbao.bank;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration      // 代替xml配置文件
@ComponentScan("com.shirongbao.bank")       // 组件扫描
@EnableTransactionManagement        // 开启事务注解驱动器

public class Spring6Config {

    @Bean(name = "dataSource")
    public DruidDataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring6");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean(name = "jdbcTemplate")
    // 这里设置一个形参,spring在调用这个方法的时候会自动传入一个dataSource对象
    public JdbcTemplate getTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("txManager")
    public DataSourceTransactionManager getTxManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
