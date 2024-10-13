package com.atguigu.service;

import com.atguigu.vo.TrackStatVo;
import com.atguigu.vo.UserCollectVo;
import com.atguigu.vo.UserListenProcessTempVo;
import com.atguigu.vo.UserListenProcessVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/7/23 21:59
 * @description:
 */
public interface ListenService {

    /**
     * 根据专辑id，声音id，播放秒数更新播放进度
     * @param userListenProcessVo 封装好的辑id，声音id，播放秒数
     */
    void updatePlaySecond(UserListenProcessVo userListenProcessVo);


    /**
     * 查询最近播放的信息
     *
     * @return 返回一个map集合，里面存着albumId和trackId
     */
    Map<String, Object> getRecentlyPlay();


    /**
     * 根据trackId查询上次播放秒数
     *
     * @param trackId trackId
     * @return 返回最后上次播放记录的秒数
     */
    BigDecimal getLastPlaySecond(Long trackId);

    /**
     * 根据trackId获取声音的统计信息
     *
     * @param trackId trackId
     * @return 返回封装好的信息
     */
    TrackStatVo getTrackStatistics(Long trackId);

    /**
     * 获取用户是否点赞信息
     * @param trackId trackId
     * @return 点赞返回true，否则返回false
     */
    Boolean isCollect(Long trackId);

    /**
     * 点赞功能
     * @param trackId trackId
     * @return 点赞成功返回true，否则返回false
     */
    boolean collectTrack(Long trackId);

    /**
     * 获取用户声音点赞播放列表
     * @param pageNum 页码
     * @param pageSize 页数
     * @return 返回分页信息
     */
    IPage<UserCollectVo> getUserCollectByPage(Integer pageNum, Integer pageSize);

    /**
     * 获取用户声音历史播放列表
     * @param pageNum 页码
     * @param pageSize 页数
     * @return 返回分页信息
     */
    IPage<UserListenProcessTempVo> getPlayHistoryTrackByPage(Integer pageNum, Integer pageSize);
}
