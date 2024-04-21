package com.shirongbao.schedule.dao.impl;

import com.shirongbao.schedule.dao.BaseDao;
import com.shirongbao.schedule.dao.SysScheduleDao;
import com.shirongbao.schedule.pojo.SysSchedule;

import java.util.List;

public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {
    @Override
    public int addSchedule(SysSchedule schedule) {
        String sql = "INSERT INTO sys_schedule VALUES(DEFAULT, ?, ?, ?)";
        int rows = baseUpdate(sql, schedule.getUid(), schedule.getTitle(), schedule.getCompleted());
        return rows;
    }

    @Override
    public List<SysSchedule> findAll() {
        String sql = "SELECT sid, uid, title, completed FROM sys_schedule";
        List<SysSchedule> scheduleList = baseQuery(SysSchedule.class, sql);
        return scheduleList;
    }

    @Override
    public List<SysSchedule> findItemListByUid(int uid) {
        String sql = "SELECT sid, uid, title, completed FROM sys_schedule WHERE uid = ?";
        List<SysSchedule> itemList = baseQuery(SysSchedule.class, sql, uid);
        return itemList;
    }

    @Override
    public Integer addDefault(int uid) {
        String sql = "INSERT INTO sys_schedule(sid, uid, title, completed) VALUES(DEFAULT, ?, '请输入日程', 0)";
        return baseUpdate(sql, uid);
    }

    @Override
    public Integer updateSchedule(SysSchedule schedule) {
        String sql = "UPDATE sys_schedule SET title = ?, completed = ? WHERE sid = ?";
        return baseUpdate(sql, schedule.getTitle(), schedule.getCompleted(), schedule.getSid());
    }

    @Override
    public Integer removeSchedule(int sid) {
        String sql = "DELETE FROM sys_schedule WHERE sid = ?";
        return baseUpdate(sql, sid);
    }
}
