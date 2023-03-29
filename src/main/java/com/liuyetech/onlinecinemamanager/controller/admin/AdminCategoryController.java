package com.liuyetech.onlinecinemamanager.controller.admin;

import com.liuyetech.onlinecinemamanager.domain.Category;
import com.liuyetech.onlinecinemamanager.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public String categoryList(ModelMap modelMap) {
        modelMap.addAttribute("datas", categoryService.list());
        return "category/list";
    }

    @GetMapping("update")
    public String categoryUpdatePage(@RequestParam("cid") Integer cid, ModelMap modelMap) {
        modelMap.addAttribute("category", categoryService.getById(cid));
        return "category/update";
    }

    @PostMapping("update")
    public String categoryUpdate(Category category, ModelMap modelMap) {
        if (categoryService.updateById(category)) {
            modelMap.addAttribute("msg", "更新成功！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        modelMap.addAttribute("msg", "更新失败！");
        modelMap.addAttribute("url", "/admin/home");
        return "msg";
    }

    @GetMapping("delete")
    public String deleteCategory(@RequestParam("cid") Integer cid, ModelMap modelMap) {
        if (categoryService.removeById(cid)) {
            modelMap.addAttribute("msg", "删除成功！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        modelMap.addAttribute("msg", "删除失败！");
        modelMap.addAttribute("url", "/admin/home");
        return "msg";
    }

    @GetMapping("add")
    public String addCategoryPage() {
        return "category/add";
    }

    @PostMapping("add")
    public String addCategory(Category category, ModelMap modelMap) {
        if (categoryService.save(category)) {
            modelMap.addAttribute("msg", "添加成功！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        modelMap.addAttribute("msg", "添加失败！");
        modelMap.addAttribute("url", "/admin/home");
        return "msg";
    }
}
