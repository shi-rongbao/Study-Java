package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.mapper.StudentMapper;
import com.shirongbao.mybatis.pojo.Student;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentMapperTest {
    @Test
    public void testSelectByNameAndSex(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByNameAndSex("张三", '男');
        students.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void testInsertStudentByMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> student = new HashMap<>();
        student.put("name", "赵六");
        student.put("age", 26);
        student.put("height", 1.82);
        student.put("birth", "2006-04-21");
        student.put("sex", '女');
        int rows = mapper.insertStudentByMap(student);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectById(1L);
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByName() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByName("李四");
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByBirth() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = sdf.parse("2003-09-22");

        List<Student> students = mapper.selectByBirth(birth);
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectBySex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Character character = '男';
        List<Student> students = mapper.selectBySex(character);
        students.forEach(System.out::println);
        sqlSession.close();
    }
}
