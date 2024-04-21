package com.shirongbao.mybatis.mapper;

import com.shirongbao.mybatis.pojo.Student;

import java.util.List;

public interface StudentMapper {

    List<Student> selectByCidStep2(Integer cid);

    /**
     * 分步查询第一步,现根据学生的sid查询学生的信息.
     * @param id 学生的id
     * @return 学生的信息
     */
    Student selectByIdStep1(Integer id);

    /**
     * 一条SQL语句,association
     * @param id 学生的ID
     * @return 学生对象
     */
    Student selectByIdAssociation(Integer id);

    /**
     * 根据id获取学生信息,同时获取学生关联的班级信息
     * @param id 学生的id
     * @return 学生对象,但是学生对象中包含班级对象
     */
    Student selectById(Integer id);
}
