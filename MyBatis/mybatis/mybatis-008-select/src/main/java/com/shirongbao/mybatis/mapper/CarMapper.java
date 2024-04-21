package com.shirongbao.mybatis.mapper;

import com.shirongbao.mybatis.pojo.Car;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface CarMapper {
    Long selectTotal();
    List<Car> selectAllByMapUnderscoreToCamelCase();
    List<Car> selectAllByResultMap();

    @MapKey("id")
    Map<Long, Map<Long, Object>> selectAllRetMapMap();

    List<Map<String, Object>> selectAllRetListMap();

    Map<String, Object> selectByIdRetMap(Long id);

    List<Car> selectAll();


    /**
     * 根据id查询Car信息
     *
     * @param id 要查询的汽车id
     * @return 返回被查询到的汽车对象
     */
    Car selectById(Long id);
}
