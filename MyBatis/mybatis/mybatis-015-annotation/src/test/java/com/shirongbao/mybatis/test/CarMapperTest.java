package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.mapper.CarMapper;
import com.shirongbao.mybatis.pojo.Car;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;

public class CarMapperTest {
    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null, "1123", "奥迪A8", new BigDecimal("63.55"), "2024-01-27", "燃油车");
        int rows = mapper.insert(car);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

}
