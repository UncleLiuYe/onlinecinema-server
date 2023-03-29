package com.liuyetech.onlinecinemamanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyetech.onlinecinemamanager.domain.Actor;
import com.liuyetech.onlinecinemamanager.mapper.ActorMapper;
import com.liuyetech.onlinecinemamanager.service.ActorService;
import org.springframework.stereotype.Service;

/**
* @author LiuYe
* @description 针对表【tb_actor】的数据库操作Service实现
* @createDate 2023-02-08 14:40:04
*/
@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor>
    implements ActorService{

}