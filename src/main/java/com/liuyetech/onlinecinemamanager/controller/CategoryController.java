package com.liuyetech.onlinecinemamanager.controller;

import com.liuyetech.onlinecinemamanager.domain.Category;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public R<List<Category>> getAllCategory() {
        return R.success(categoryService.list(null));
    }

    @GetMapping("{cid}")
    public R<Category> getCategoryByCategoryId(@PathVariable("cid") Integer cid) {
        return R.success(categoryService.getById(cid));
    }
}
