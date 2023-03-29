package com.liuyetech.onlinecinemamanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyetech.onlinecinemamanager.domain.News;
import com.liuyetech.onlinecinemamanager.mapper.NewsMapper;
import com.liuyetech.onlinecinemamanager.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
        implements NewsService {

}