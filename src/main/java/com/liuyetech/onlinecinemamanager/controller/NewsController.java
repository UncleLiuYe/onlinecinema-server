package com.liuyetech.onlinecinemamanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyetech.onlinecinemamanager.domain.News;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("list")
    public R<List<News>> newsList() {
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        return R.success(newsService.list(newsQueryWrapper));
    }

    @GetMapping("{nid}")
    public R<News> getNewsId(@PathVariable("nid") Integer nid) {
        return R.success(newsService.getById(nid));
    }
}
