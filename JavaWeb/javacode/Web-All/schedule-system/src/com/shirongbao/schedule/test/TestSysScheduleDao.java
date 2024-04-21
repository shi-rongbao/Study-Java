package com.shirongbao.schedule.test;

import com.shirongbao.schedule.dao.SysScheduleDao;
import com.shirongbao.schedule.dao.impl.SysScheduleDaoImpl;
import com.shirongbao.schedule.pojo.SysSchedule;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TestSysScheduleDao {

    private static SysScheduleDao scheduleDao;


    @BeforeClass
    public static void initScheduleDao() {
        scheduleDao = new SysScheduleDaoImpl();
    }

    @Test
    public void testAddSchedule() {
        int rows = scheduleDao.addSchedule(new SysSchedule(null, 2, "学习JAVA", 1));
        System.out.println(rows);
    }

    @Test
    public void testFindAll(){
        List<SysSchedule> list = scheduleDao.findAll();
        list.forEach(System.out::println);
    }
}
