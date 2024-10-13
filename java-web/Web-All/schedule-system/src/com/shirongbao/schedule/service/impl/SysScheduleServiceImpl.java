package com.shirongbao.schedule.service.impl;

import com.shirongbao.schedule.dao.SysScheduleDao;
import com.shirongbao.schedule.dao.impl.SysScheduleDaoImpl;
import com.shirongbao.schedule.pojo.SysSchedule;
import com.shirongbao.schedule.service.SysScheduleService;

import java.util.List;

public class SysScheduleServiceImpl implements SysScheduleService {
    private SysScheduleDao scheduleDao = new SysScheduleDaoImpl();
    @Override
    public List<SysSchedule> findItemListByUid(int uid) {
        return scheduleDao.findItemListByUid(uid);
    }

    @Override
    public Integer addDefault(int uid) {
        return scheduleDao.addDefault(uid);
    }

    @Override
    public Integer updateSchedule(SysSchedule schedule) {
        return scheduleDao.updateSchedule(schedule);
    }

    @Override
    public Integer removeSchedule(int sid) {
        return scheduleDao.removeSchedule(sid);
    }
}
