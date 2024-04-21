package com.shirongbao.boot.ssm.mapper;

import com.shirongbao.boot.ssm.bean.TUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ShiRongbao
 * @create 2024/2/22 16:41
 * @description:
 */
@Mapper
public interface UserMapper {
    TUser getUserById(@Param("id") Long id);

    @Insert("insert into user(id, name) values(1,'srb')")
    void insertTest();
}
