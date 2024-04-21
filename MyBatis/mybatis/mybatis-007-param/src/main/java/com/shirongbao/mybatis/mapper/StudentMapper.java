package com.shirongbao.mybatis.mapper;

import com.shirongbao.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StudentMapper {

    /**
     * 根据name和sex查询student信息
     * @param name 要查询的student的name
     * @param sex 要查询的student的sex
     * @return 返回查询到的学生的信息
     */
    List<Student> selectByNameAndSex(@Param("name") String name, @Param("sex") Character sex);

    /**
     * 保存学生信息,通过map参数,以下是单个参数,但是参数类型不是简单类型,是map集合
     * @param map 要传入的map集合,key -> 字段名 value -> 对应的数据
     */
    int insertStudentByMap(Map<String, Object> map);

    /**
     * 当借口中的方法的参数只有一个,并且参数的数据类型都是简单类型
     * 根据id查询,根据name查询,birth查询,sex查询
     */
    List<Student> selectById(Long id);

    List<Student> selectByName(String name);

    List<Student> selectByBirth(Date birth);

    List<Student> selectBySex(Character sex);
}
