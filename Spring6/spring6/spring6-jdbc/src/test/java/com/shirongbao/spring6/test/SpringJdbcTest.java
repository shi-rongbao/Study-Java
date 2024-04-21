package com.shirongbao.spring6.test;

import com.shirongbao.spring6.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpringJdbcTest {
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    private JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

    @Test
    public void testBatchDelete() {
        String sql = "DELETE FROM t_user WHERE id = ?;";
        Object[] i1 = {9};
        Object[] i2 = {5};
        Object[] i3 = {6};
        Object[] i4 = {7};
        Object[] i5 = {8};

        List<Object[]> list = new ArrayList<>();
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        list.add(i5);
        int[] rows = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(rows));
    }

    @Test
    public void testAddBatch() {
        String sql = "INSERT INTO t_user(real_name, age) VALUES(?, ?);";
        Object[] us1 = {"山本", 118};
        Object[] us2 = {"田野", 219};
        Object[] us3 = {"仙人", 320};

        List<Object[]> list = new ArrayList<>();
        list.add(us1);
        list.add(us2);
        list.add(us3);

        int[] rows = jdbcTemplate.batchUpdate(sql, list);
        System.out.println(Arrays.toString(rows));
    }

    @Test
    public void testQueryOneValue() {
        String sql = "SELECT COUNT(*) FROM t_user";
        Integer count = jdbcTemplate.queryForObject(sql, int.class);
        System.out.println(count);
    }

    @Test
    public void testQueryAll() {
        String sql = "SELECT * FROM t_user;";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        users.forEach(System.out::println);
    }

    // 查询并获取到这个对象
    @Test
    public void tQueryOne() {
        String sql = "SELECT * FROM t_user WHERE id = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 3);
        System.out.println(user);
    }

    @Test
    public void testDelete() {
        String sql = "DELETE FROM t_user WHERE id = ?";
        int rows = jdbcTemplate.update(sql, 2);
        System.out.println(rows);
    }

    @Test
    public void testUpdate() {
        String sql = "UPDATE t_user SET age = 250 WHERE id = ?";
        int rows = jdbcTemplate.update(sql, 3);
        System.out.println(rows);
    }

    @Test
    public void testInsert() {
        String sql = "INSERT INTO t_user(real_name, age) VALUES(?, ?);";
        int rows = jdbcTemplate.update(sql, "王五", 25);
        System.out.println(rows);
    }

    @Test
    public void testSpringJdbc() {
        System.out.println(jdbcTemplate);
    }
}
