package com.liuyetech.onlinecinemamanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyetech.onlinecinemamanager.domain.Category;
import com.liuyetech.onlinecinemamanager.service.CategoryService;
import com.liuyetech.onlinecinemamanager.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author LiuYe
* @description 针对表【tb_category】的数据库操作Service实现
* @createDate 2023-02-09 10:40:40
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




