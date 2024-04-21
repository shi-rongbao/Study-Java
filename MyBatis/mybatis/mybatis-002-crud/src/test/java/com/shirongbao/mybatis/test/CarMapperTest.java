package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.pojo.Car;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarMapperTest {
    @Test
    public void testNamespace(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        List<Object> list = sqlSession.selectList("fuck.selectAll");
        list.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        List<Car> list = sqlSession.selectList("selectAll");
        System.out.println(list);
        list.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = (Car) sqlSession.selectOne("selectById", 10);
        System.out.println(car);
        sqlSession.close();
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 通过get方法获取到数据
        Car car = new Car(4L, "1110", "特斯拉fuck3", 23.99, "2024-01-01", "新能源");
        int rows = sqlSession.update("updateById", car);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int rows = sqlSession.delete("deleteById", 12);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCarByPOJO() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car(null, "1113", "特斯拉cyber", 52.99, "2024-01-09", "新能源");
        int rows = sqlSession.insert("insertCar", car);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCar() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Map<String, Object> map = new HashMap<>();
        map.put("carNum", "1112");
        map.put("brand", "特斯拉fuckYou2");
        map.put("guidePrice", 23.96);
        map.put("produceTime", "2024-01-10");
        map.put("carType", "新能源");

        sqlSession.insert("insertCar", map);
        sqlSession.commit();
        sqlSession.close();
    }
}
