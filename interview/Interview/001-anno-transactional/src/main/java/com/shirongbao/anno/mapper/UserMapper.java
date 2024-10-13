package com.shirongbao.anno.mapper;

import com.shirongbao.anno.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ShiRongbao
 * @create 2024/10/8
 * @description:
 */
@Mapper
public interface UserMapper {

    /**
     * 插入一条数据
     *
     * @param user user记录
     */
    void insertOne(@Param("user") User user);

}
