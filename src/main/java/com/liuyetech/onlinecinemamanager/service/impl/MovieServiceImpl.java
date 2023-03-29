package com.liuyetech.onlinecinemamanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyetech.onlinecinemamanager.domain.Movie;
import com.liuyetech.onlinecinemamanager.service.MovieService;
import com.liuyetech.onlinecinemamanager.mapper.MovieMapper;
import org.springframework.stereotype.Service;

/**
* @author LiuYe
* @description 针对表【tb_movie】的数据库操作Service实现
* @createDate 2023-02-10 12:20:20
*/
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie>
    implements MovieService{

}




