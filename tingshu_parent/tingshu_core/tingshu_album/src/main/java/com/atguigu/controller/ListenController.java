package com.atguigu.controller;

import com.atguigu.login.TingShuLogin;
import com.atguigu.result.RetVal;
import com.atguigu.service.ListenService;
import com.atguigu.vo.TrackStatVo;
import com.atguigu.vo.UserCollectVo;
import com.atguigu.vo.UserListenProcessTempVo;
import com.atguigu.vo.UserListenProcessVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/7/23 21:57
 * @description:
 */
@Tag(name = "听专辑管理接口")
@RestController
@RequestMapping("/api/album/progress")
public class ListenController {

    @Resource
    private ListenService listenService;

    @TingShuLogin
    @Operation(summary = "更新播放进度")
    @PostMapping("/updatePlaySecond")
    public RetVal<Object> updatePlaySecond(@RequestBody UserListenProcessVo userListenProcessVo) {
        listenService.updatePlaySecond(userListenProcessVo);
        return RetVal.ok();
    }

    @TingShuLogin
    @Operation(summary = "获取最近播放")
    @GetMapping("/getRecentlyPlay")
    public RetVal<Map<String, Object>> getRecentlyPlay() {
        Map<String, Object> recentlyPlayMap = listenService.getRecentlyPlay();
        if (recentlyPlayMap != null) {
            return RetVal.ok(recentlyPlayMap);
        }
        return RetVal.ok();
    }

    @TingShuLogin
    @Operation(summary = "根据trackId查询上次播放秒数")
    @GetMapping("/getLastPlaySecond/{trackId}")
    public RetVal<BigDecimal> getLastPlaySecond(@PathVariable Long trackId) {
        BigDecimal lastPlaySecond = listenService.getLastPlaySecond(trackId);
        return RetVal.ok(lastPlaySecond);
    }

    @Operation(summary = "获取声音的统计信息")
    @GetMapping("/getTrackStatistics/{trackId}")
    public RetVal<TrackStatVo> getTrackStatistics(@PathVariable Long trackId) {
        TrackStatVo trackStatVo = listenService.getTrackStatistics(trackId);
        return RetVal.ok(trackStatVo);
    }

    @TingShuLogin
    @Operation(summary = "点赞")
    @GetMapping("/collectTrack/{trackId}")
    public RetVal<Boolean> collectTrack(@PathVariable Long trackId) {
        boolean flag = listenService.collectTrack(trackId);
        return RetVal.ok(flag);
    }

    @TingShuLogin
    @Operation(summary = "获取用户是否点赞信息")
    @GetMapping("/isCollect/{trackId}")
    public RetVal<Boolean> isCollect(@PathVariable Long trackId) {
        Boolean isCollect = listenService.isCollect(trackId);
        return RetVal.ok(isCollect);
    }

    @TingShuLogin
    @Operation(summary = "获取用户声音点赞播放列表")
    @GetMapping("/getUserCollectByPage/{pageNum}/{pageSize}")
    public RetVal<IPage<UserCollectVo>> getUserCollectByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        IPage<UserCollectVo> pageParam = listenService.getUserCollectByPage(pageNum, pageSize);
        return RetVal.ok(pageParam);
    }

    @TingShuLogin
    @Operation(summary = "获取用户声音历史播放列表")
    @GetMapping("/getPlayHistoryTrackByPage/{pageNum}/{pageSize}")
    public RetVal<IPage<UserListenProcessTempVo>> getPlayHistoryTrackByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        IPage<UserListenProcessTempVo> pageParam = listenService.getPlayHistoryTrackByPage(pageNum, pageSize);
        return RetVal.ok(pageParam);
    }
}
