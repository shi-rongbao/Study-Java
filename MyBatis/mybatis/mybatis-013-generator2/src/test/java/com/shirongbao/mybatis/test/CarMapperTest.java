package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.mapper.CarMapper;
import com.shirongbao.mybatis.pojo.Car;
import com.shirongbao.mybatis.pojo.CarExample;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelect(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectByPrimaryKey(20L);
        List<Car> cars = mapper.selectByExample(null);
        cars.forEach(System.out::println);
        System.out.println("==============================");
        System.out.println(car);
        System.out.println("==============================");
        CarExample carExample = new CarExample();
        carExample.createCriteria().andBrandLike("特斯拉").andGuidePriceGreaterThan(BigDecimal.valueOf(20));
        carExample.or().andCarTypeEqualTo("燃油车");
        List<Car> cars2 = mapper.selectByExample(carExample);
        cars2.forEach(System.out::println);
        sqlSession.close();
    }
}
