package com.atguigu.controller;

import com.atguigu.entity.AlbumInfo;
import com.atguigu.service.AlbumInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.redisson.api.RBloomFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/6/10 12:52
 * @description:
 */
@Tag(name = "并发遍历接口")
@RestController
@RequestMapping("/api/album")
public class ConcurrentController {

    @Resource
    private RBloomFilter<Long> bloomFilter;

    @Resource
    private AlbumInfoService albumInfoService;

    @Operation(summary = "初始化布隆过滤器")
    @GetMapping("/init")
    public void init() {
        // 查询专辑表中所有的id
        LambdaQueryWrapper<AlbumInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(AlbumInfo::getId);
        // 拿到所有的albumInfo
        List<AlbumInfo> albumInfoList = albumInfoService.list(wrapper);
        // 遍历这个集合
        albumInfoList.forEach(albumInfo -> {
            // 拿到所有id
            Long id = albumInfo.getId();
            // 放入布隆过滤器中
            bloomFilter.add(id);
        });
    }
}
