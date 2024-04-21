package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.mapper.StudentMapper;
import com.shirongbao.mybatis.pojo.Student;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class StudentMapperTest {
    @Test
    public void testSelectByIdStep1() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdStep1(5);
        System.out.println(student.getSname());
//        System.out.println(student);
        System.out.println(student.getClazz().getCid());
        sqlSession.close();
    }

    @Test
    public void testSelectByIdAssociation() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdAssociation(3);
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectById(3);
        System.out.println(student);
        sqlSession.close();
    }
}
