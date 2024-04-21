package com.shirongbao.mybatis.mapper;

import com.shirongbao.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {

    List<Car> selectAll();

    List<Car> selectByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
}