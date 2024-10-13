package com.shirongbao.mybatis.mapper;

import com.shirongbao.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Insert;

public interface CarMapper {


    @Insert("INSERT INTO t_car VALUES (DEFAULT, #{carNum}, #{brand}, #{guidePrice}, #{produceTime}, #{carType})")
    int insert(Car car);
}