package cn.shirongbao.dao.impl;

import cn.shirongbao.dao.StudentDao;
import org.springframework.stereotype.Repository;

@Repository("studentDaoImplForMySQL")
public class StudentDaoImplForMySQL implements StudentDao {
    @Override
    public void deleteById() {
        System.out.println("MySQL数据库正在根据ID删除学生信息");
    }
}
