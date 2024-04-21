package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.mapper.ClazzMapper;
import com.shirongbao.mybatis.pojo.Clazz;
import com.shirongbao.mybatis.pojo.Student;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ClazzMapperTest {
    @Test
    public void testClazzResultMapStep(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByStep1(10001);
        System.out.println(clazz);
        sqlSession.close();
    }
    @Test
    public void testSelectByCollection(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCollection(10001);
        List<Student> students = clazz.getStudents();
        students.forEach(System.out::println);
        System.out.println(clazz);
        sqlSession.close();
    }
}
