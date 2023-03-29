package com.liuyetech.onlinecinemamanager.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyetech.onlinecinemamanager.domain.News;
import com.liuyetech.onlinecinemamanager.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("admin/news")
public class AdminNewsController {
    @Autowired
    private NewsService newsService;

    @Value("${nginx.img-path}")
    private String nginxImgPath;

    @GetMapping("list")
    public String newsList(Integer currentPage, ModelMap modelMap) {
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }
        IPage<News> page = new Page<>(currentPage, 8);
        modelMap.addAttribute("datas", newsService.page(page));
        return "news/list";
    }

    @PostMapping("upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file) throws IOException {
        Map<String, Object> res = new HashMap<>();
        if (file.isEmpty()) {
            res.put("errno", 1);
            res.put("message", "文件为空");
            return res;
        }
        File uploadFile = new File(nginxImgPath + "/" + file.getOriginalFilename());
        if (!uploadFile.exists()) {
            file.transferTo(uploadFile);
        }
        res.put("errno", 0);
        Map<String, String> data = new HashMap<>();
        data.put("url", "http://192.168.1.2:80/imgs/" + file.getOriginalFilename());
        data.put("alt", "...");
        res.put("data", data);
        return res;
    }

    @GetMapping("add")
    public String addNewsPage() {
        return "news/add";
    }

    @PostMapping("add")
    @ResponseBody
    public String addNews(News news) {
        log.info(news.toString());
        news.setNewsCreaterId(1);
        news.setNewsCreatetime(LocalDateTime.now());
        if (newsService.save(news)) {
            return "ok";
        } else {
            return "fail";
        }
    }

    @GetMapping("show")
    public String showNews(@RequestParam("nid") Integer nid, ModelMap modelMap) {
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.eq("news_id", nid);

        modelMap.addAttribute("news", newsService.getOne(newsQueryWrapper));
        return "news/show";
    }

    @GetMapping("delete")
    public String deleteNews(@RequestParam("nid") Integer nid, ModelMap modelMap) {
        if (newsService.removeById(nid)) {
            modelMap.addAttribute("msg", "删除成功！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        modelMap.addAttribute("msg", "删除失败！");
        modelMap.addAttribute("url", "/admin/home");
        return "msg";
    }

    @PostMapping("searchNews")
    public String searchNews(String newsTitle, Integer currentPage, ModelMap modelMap) {
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(newsTitle)) {
            newsQueryWrapper.like("news_title", newsTitle);
        }
        IPage<News> page = new Page<>(currentPage, 8);
        modelMap.addAttribute("datas", newsService.page(page, newsQueryWrapper));
        return "news/list";
    }

    @GetMapping("update")
    public String updateNewsPage(@RequestParam("nid") Integer nid, ModelMap modelMap) {
        QueryWrapper<News> newsQueryWrapper = new QueryWrapper<>();
        newsQueryWrapper.eq("news_id", nid);
        modelMap.addAttribute("news", newsService.getOne(newsQueryWrapper));
        return "news/update";
    }

    @PostMapping("update")
    @ResponseBody
    public String updateNews(News news) {
        log.info(news.toString());
        if (newsService.updateById(news)) {
            return "ok";
        }
        return "fail";
    }
}
