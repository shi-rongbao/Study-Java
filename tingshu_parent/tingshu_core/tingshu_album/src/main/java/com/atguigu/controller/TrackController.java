package com.atguigu.controller;

import com.atguigu.cache.TingShuCache;
import com.atguigu.entity.AlbumInfo;
import com.atguigu.entity.TrackInfo;
import com.atguigu.login.TingShuLogin;
import com.atguigu.mapper.TrackInfoMapper;
import com.atguigu.query.TrackInfoQuery;
import com.atguigu.result.RetVal;
import com.atguigu.service.AlbumInfoService;
import com.atguigu.service.TrackInfoService;
import com.atguigu.service.VodService;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.vo.AlbumTrackListVo;
import com.atguigu.vo.TrackTempVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.simpleframework.xml.Path;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/5/17 13:53
 * @description:
 */
@Tag(name = "声音管理")
@RestController
@RequestMapping("/api/album/trackInfo")
public class TrackController {

    @Resource
    private AlbumInfoService albumInfoService;

    @Resource
    private VodService vodService;

    @Resource
    private TrackInfoService trackInfoService;

    @Resource
    private TrackInfoMapper trackInfoMapper;

    @TingShuLogin
    @Operation(summary = "根据用户的id查询用户的专辑信息")
    @GetMapping("/findAlbumByUserId")
    public RetVal<List<AlbumInfo>> findAlbumByUserId() {
        // 拿到当前的userId
        Long userId = AuthContextHolder.getUserId();
        // 创建过滤条件
        LambdaQueryWrapper<AlbumInfo> wrapper = new LambdaQueryWrapper<>();
        // 根据id查询
        wrapper.eq(AlbumInfo::getUserId, userId);
        // 只查询userId和专辑标题
        wrapper.select(AlbumInfo::getId, AlbumInfo::getAlbumTitle);
        List<AlbumInfo> albumInfoList = albumInfoService.list(wrapper);
        return RetVal.ok(albumInfoList);
    }

    // @TingShuLogin  前端发请求没带token，所以不能校验登录状态
    @Operation(summary = "上传声音")
    @PostMapping("/uploadTrack")
    public RetVal<Map<String, String>> uploadTrack(MultipartFile file) {
        Map<String, String> retMap = vodService.uploadTrack(file);
        return RetVal.ok(retMap);
    }

    @TingShuLogin
    @Operation(summary = "保存声音信息")
    @PostMapping("/saveTrackInfo")
    public RetVal<String> saveTrackInfo(@RequestBody() TrackInfo trackInfo) {
        boolean save = trackInfoService.saveTrackInfo(trackInfo);
        if (save) {
            return RetVal.ok();
        } else {
            return RetVal.fail("保存声音信息失败");
        }
    }

    @TingShuLogin
    @Operation(summary = "声音信息分页查询")
    @PostMapping("/findUserTrackPage/{pageNum}/{pageSize}")
    public RetVal<IPage<TrackTempVo>> findUserTrackPage(
            @Parameter(name = "pageNum", description = "当前页码", required = true)
            @PathVariable Long pageNum,
            @Parameter(name = "pageSize", description = "每页记录数", required = true)
            @PathVariable Long pageSize,
            @Parameter(name = "trackInfoQuery", description = "查询对象")
            @RequestBody
            TrackInfoQuery trackInfoQuery) {
        // 先拿到userId
        Long userId = AuthContextHolder.getUserId();
        trackInfoQuery.setUserId(userId);
        // 创建分页对象
        IPage<TrackTempVo> pageParam = new Page<>(pageNum, pageSize);
        pageParam = trackInfoMapper.findUserTrackPage(pageParam, trackInfoQuery);
        return RetVal.ok(pageParam);
    }

    @TingShuCache(value = "trackInfo", enableBloom = false)
    @TingShuLogin
    @Operation(summary = "根据id查询声音详情")
    @GetMapping("/getTrackInfoById/{trackId}")
    public RetVal<TrackInfo> getTrackInfoById(
            @Parameter(name = "trackId", description = "声音id", required = true)
            @PathVariable Long trackId) {
        TrackInfo trackInfo = trackInfoService.getById(trackId);
        return RetVal.ok(trackInfo);
    }

    @TingShuLogin
    @Operation(summary = "修改声音信息")
    @PutMapping("/updateTrackInfoById")
    public RetVal<Object> updateTrackInfoById(@RequestBody TrackInfo trackInfo) {
        boolean update = trackInfoService.updateTrackInfoById(trackInfo);
        if (update) {
            return RetVal.ok();
        } else {
            return RetVal.fail();
        }
    }

    @TingShuLogin
    @Operation(summary = "删除声音信息")
    @DeleteMapping("/deleteTrackInfo/{trackId}")
    public RetVal<Object> deleteTrackInfo(@PathVariable Long trackId) {
        boolean delete = trackInfoService.deleteTrackInfo(trackId);
        if (delete) {
            return RetVal.ok();
        } else {
            return RetVal.fail();
        }
    }

    @TingShuLogin
    @Operation(summary = "根据专辑id分页查询专辑声音详情")
    @GetMapping("/getAlbumDetailTrackByPage/{albumId}/{pageNum}/{pageSize}")
    public RetVal<Page<AlbumTrackListVo>> getAlbumDetailTrackByPage(@PathVariable Long albumId, @PathVariable Long pageNum, @PathVariable Long pageSize) {
        System.out.println(AuthContextHolder.getUserId());
        Page<AlbumTrackListVo> pageParam = new Page<>(pageNum, pageSize);
        pageParam = trackInfoService.getAlbumDetailTrackByPage(pageParam, albumId);
        return RetVal.ok(pageParam);
    }

    @TingShuLogin
    @Operation(summary = "根据trackId查询用户购买的声音列表")
    @GetMapping("/getTrackListToChoose/{trackId}")
    public RetVal<List<Map<String, Object>>> getTrackListToChoose(@PathVariable Long trackId) {
        List<Map<String, Object>> trackListToChoose = trackInfoService.getTrackListToChoose(trackId);
        return RetVal.ok(trackListToChoose);
    }

    @Operation(summary = "获取要购买的声音的列表")
    @GetMapping("/getTrackListPrepareToBuy/{trackId}/{trackCount}")
    public RetVal<List<TrackInfo>> getTrackListPrepareToBuy(@PathVariable Long trackId, @PathVariable Integer trackCount) {
        List<TrackInfo> trackInfoList = trackInfoService.getTrackListPrepareToBuy(trackId, trackCount);
        return RetVal.ok(trackInfoList);
    }
}
