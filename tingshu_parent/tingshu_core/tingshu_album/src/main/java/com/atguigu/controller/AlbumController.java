package com.atguigu.controller;

import com.atguigu.SearchFeignClient;
import com.atguigu.entity.AlbumAttributeValue;
import com.atguigu.entity.AlbumInfo;
import com.atguigu.login.TingShuLogin;
import com.atguigu.mapper.AlbumInfoMapper;
import com.atguigu.mapper.AlbumStatMapper;
import com.atguigu.query.AlbumInfoQuery;
import com.atguigu.result.RetVal;
import com.atguigu.service.AlbumAttributeValueService;
import com.atguigu.service.AlbumInfoService;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.vo.AlbumInfoVo;
import com.atguigu.vo.AlbumStatVo;
import com.atguigu.vo.AlbumTempVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import kotlin.jvm.internal.Lambda;
import org.redisson.api.RedissonClient;
import org.simpleframework.xml.Path;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/5/16 16:43
 * @description: 专辑相关控制器方法
 */
@Tag(name = "专辑管理")
@RestController
@RequestMapping("/api/album/albumInfo")
public class AlbumController {

    @Resource
    private AlbumInfoService albumInfoService;

    @Resource
    private AlbumInfoMapper albumInfoMapper;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private AlbumStatMapper albumStatMapper;

    @TingShuLogin
    @Operation(summary = "新增专辑")
    @PostMapping("/saveAlbumInfo")
    public RetVal<String> saveAlbumInfo(@RequestBody AlbumInfoVo albumInfoVo) {
        Boolean flag = albumInfoService.saveAlbumInfo(albumInfoVo);
        if (flag) {
            return RetVal.ok();
        } else {
            return RetVal.fail("保存失败");
        }
    }

    @TingShuLogin
    @Operation(summary = "分页查询专辑")
    @PostMapping("/getUserAlbumByPage/{pageNum}/{pageSize}")
    public RetVal<IPage<AlbumTempVo>> getUserAlbumByPage(
            @Parameter(name = "pageNum", description = "当前页码", required = true)
            @PathVariable Long pageNum,
            @Parameter(name = "pageSize", description = "每页记录数", required = true)
            @PathVariable Long pageSize,
            @Parameter(name = "albumInfoQuery", description = "查询对象")
            @RequestBody AlbumInfoQuery albumInfoQuery) {
        Long userId = AuthContextHolder.getUserId();
        albumInfoQuery.setUserId(userId);
        IPage<AlbumTempVo> pageParam = new Page<>(pageNum, pageSize);
        pageParam = albumInfoMapper.getUserAlbumByPage(pageParam, albumInfoQuery);
        return RetVal.ok(pageParam);
    }

    @Operation(summary = "根据专辑id查询专辑信息")
    @GetMapping("/getAlbumInfoById/{albumId}")
    public RetVal<AlbumInfo> getAlbumInfoById(@PathVariable Long albumId) {
        AlbumInfo albumInfo = albumInfoService.getAlbumInfoById(albumId);
        return RetVal.ok(albumInfo);
    }

    @TingShuLogin
    @Operation(summary = "修改专辑信息")
    @PutMapping("/updateAlbumInfo")
    public RetVal<Object> updateAlbumInfo(@RequestBody AlbumInfo albumInfo) {
        boolean update = albumInfoService.updateAlbumInfo(albumInfo);
        if (update) {
            return RetVal.ok();
        } else {
            return RetVal.fail();
        }
    }

    @TingShuLogin
    @Operation(summary = "删除专辑信息")
    @DeleteMapping("/deleteAlbumInfo/{albumId}")
    public RetVal<Object> deleteAlbumInfo(@PathVariable Long albumId) {
        boolean delete = albumInfoService.deleteAlbumInfo(albumId);
        if (delete) {
            return RetVal.ok();
        } else {
            return RetVal.fail();
        }
    }

    @TingShuLogin
    @Operation(summary = "是否订阅查询")
    @GetMapping("/isSubscribe/{albumId}")
    public RetVal<Object> isSubscribe(@PathVariable Long albumId){
        boolean flag = albumInfoService.isSubscribe(albumId);
        return RetVal.ok(flag);
    }

    /**
     * 以下内容属于搜索模块
     **/
    @Resource
    private AlbumAttributeValueService albumAttributeValueService;


    @Operation(summary = "根据albumId查询专辑属性值")
    @GetMapping("/getAlbumPropertyValue/{albumId}")
    public RetVal<List<AlbumAttributeValue>> getAlbumPropertyValue(@PathVariable Long albumId) {
        LambdaQueryWrapper<AlbumAttributeValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AlbumAttributeValue::getAlbumId, albumId);
        List<AlbumAttributeValue> albumAttributeValueList = albumAttributeValueService.list(wrapper);
        return RetVal.ok(albumAttributeValueList);
    }

    @Operation(summary = "获取专辑统计信息")
    @GetMapping("/getAlbumStatInfo/{albumId}")
    public AlbumStatVo getAlbumStatInfo(@PathVariable Long albumId){
        return albumStatMapper.getAlbumStatInfo(albumId);
    }


}
