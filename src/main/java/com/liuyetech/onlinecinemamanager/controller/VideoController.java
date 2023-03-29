package com.liuyetech.onlinecinemamanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyetech.onlinecinemamanager.domain.Video;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("list")
    public R<List<Video>> getAllVideo(Integer cid) {
        if (cid == null) {
            return R.success(videoService.list(null));
        } else {
            QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
            videoQueryWrapper.eq("video_type", cid);
            return R.success(videoService.list(videoQueryWrapper));
        }
    }

    @GetMapping("{id}")
    public R<Video> getVideoInfo(@PathVariable("id") Integer id) {
        return R.success(videoService.getById(id));
    }

    @GetMapping("type/{cid}")
    public R<List<Video>> getVideoByTpye(@PathVariable("cid") Integer cid) {
        if (cid == null) {
            return R.fail("非法参数");
        }
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_type", cid);
        return R.success(videoService.list(queryWrapper));
    }
}
