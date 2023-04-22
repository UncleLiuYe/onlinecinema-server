package com.liuyetech.onlinecinemamanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyetech.onlinecinemamanager.domain.Actor;
import com.liuyetech.onlinecinemamanager.mapper.ActorMapper;
import com.liuyetech.onlinecinemamanager.service.ActorService;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor>
    implements ActorService{

}