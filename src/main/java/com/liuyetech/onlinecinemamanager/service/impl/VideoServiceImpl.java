package com.liuyetech.onlinecinemamanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyetech.onlinecinemamanager.domain.Video;
import com.liuyetech.onlinecinemamanager.service.VideoService;
import com.liuyetech.onlinecinemamanager.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author LiuYe
* @description 针对表【tb_video】的数据库操作Service实现
* @createDate 2023-02-08 14:43:49
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




