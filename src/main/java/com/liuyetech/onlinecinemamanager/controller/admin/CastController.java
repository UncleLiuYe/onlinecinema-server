package com.liuyetech.onlinecinemamanager.controller.admin;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyetech.onlinecinemamanager.domain.Cast;
import com.liuyetech.onlinecinemamanager.domain.MovieCast;
import com.liuyetech.onlinecinemamanager.entity.MovieCastVo;
import com.liuyetech.onlinecinemamanager.service.CastService;
import com.liuyetech.onlinecinemamanager.service.MovieCastService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("admin/cast")
public class CastController {

    @Autowired
    private CastService castService;

    @Autowired
    private MovieCastService movieCastService;
    @Autowired
    private OSSClient ossClient;

    @GetMapping("list")
    public String castList(Integer currentPage, ModelMap modelMap) {
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }
        IPage<Cast> page = new Page<>(currentPage, 8);
        modelMap.addAttribute("casts", castService.page(page));
        return "cast/list";
    }


    @GetMapping("addCast")
    public String addCastList(ModelMap modelMap) {
        modelMap.addAttribute("casts", castService.list());
        return "cast/alist";
    }

    @GetMapping("add")
    public String addCastPage() {
        return "cast/add";
    }

    @PostMapping("add")
    public String addCast(MultipartFile castImg, HttpServletRequest request, ModelMap modelMap) throws InvocationTargetException, IllegalAccessException {
        if (castImg == null || castImg.isEmpty()) {
            modelMap.addAttribute("msg", "文件不能为空！");
            modelMap.addAttribute("url", "/admin/cast/list");
            return "msg";
        }
        try {
            boolean isExists = ossClient.doesObjectExist("onlinecinema", castImg.getOriginalFilename());
            if (!isExists) {
                PutObjectRequest putObjectRequest = new PutObjectRequest("onlinecinema"
                        , "image/" + castImg.getOriginalFilename(), castImg.getInputStream());
                putObjectRequest.setProcess("true");
                ossClient.putObject(putObjectRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("msg", "文件上传失败！");
            modelMap.addAttribute("url", "/admin/cast/list");
            return "msg";
        }

        Map<String, String[]> params = request.getParameterMap();
        Cast cast = new Cast();
        cast.setCastProfileImg("/" + castImg.getOriginalFilename());
        BeanUtils.populate(cast, params);

        if (castService.save(cast)) {
            modelMap.addAttribute("msg", "添加成功！");
            modelMap.addAttribute("url", "/admin/cast/list");
            return "msg";
        }
        modelMap.addAttribute("msg", "添加失败！");
        modelMap.addAttribute("url", "/admin/cast/list");
        return "msg";
    }

    @PostMapping("search")
    public String searchACast(@RequestParam("castName") String castName, ModelMap modelMap) {
        QueryWrapper<Cast> castQueryWrapper = new QueryWrapper<>();
        castQueryWrapper.like("cast_name", castName);
        modelMap.addAttribute("casts", castService.list(castQueryWrapper));
        return "cast/alist";
    }

    @PostMapping("searchCast")
    public String searchCast(@RequestParam("castName") String castName, Integer currentPage, ModelMap modelMap) {
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }
        IPage<Cast> page = new Page<>(currentPage, 8);
        QueryWrapper<Cast> castQueryWrapper = new QueryWrapper<>();
        castQueryWrapper.like("cast_name", castName);
        modelMap.addAttribute("casts", castService.page(page, castQueryWrapper));
        return "cast/list";
    }

    @PostMapping("updateMovieCasts")
    @ResponseBody
    public Integer updateMovieCasts(@RequestBody MovieCastVo movieCastVo) {
        log.info(movieCastVo.toString());
        QueryWrapper<MovieCast> movieCastServiceQueryWrapper = new QueryWrapper<>();
        movieCastServiceQueryWrapper.eq("movie_cast_movie_id", movieCastVo.getMid());
        List<MovieCast> movieCasts = movieCastService.list(movieCastServiceQueryWrapper);
        for (MovieCast movieCast : movieCasts) {
            movieCastService.removeById(movieCast);
        }
        movieCastVo.getCastIds().forEach(s -> {
            MovieCast movieCast = new MovieCast();
            movieCast.setMovieCastMovieId(movieCastVo.getMid());
            movieCast.setMovieCastCastId(Integer.parseInt(s));
            movieCastService.save(movieCast);
        });
        return 1;
    }

    @GetMapping("update")
    public String updateCastPage(@RequestParam("id") Integer id, ModelMap modelMap) {
        QueryWrapper<Cast> castQueryWrapper = new QueryWrapper<>();
        castQueryWrapper.eq("cast_id", id);
        modelMap.addAttribute("cast", castService.getOne(castQueryWrapper));
        return "cast/update";
    }

    @PostMapping("update")
    public String updateCast(MultipartFile castImg, HttpServletRequest request, ModelMap modelMap) throws InvocationTargetException, IllegalAccessException {
        String fileName = null;
        if (castImg != null && castImg.getSize() > 0) {
            try {
                boolean isExists = ossClient.doesObjectExist("onlinecinema", castImg.getOriginalFilename());
                if (!isExists) {
                    PutObjectRequest putObjectRequest = new PutObjectRequest("onlinecinema"
                            , "image/" + castImg.getOriginalFilename(), castImg.getInputStream());
                    putObjectRequest.setProcess("true");
                    ossClient.putObject(putObjectRequest);
                }
                fileName = "/" + castImg.getOriginalFilename();
            } catch (Exception e) {
                e.printStackTrace();
                modelMap.addAttribute("msg", "文件上传失败！");
                modelMap.addAttribute("url", "/admin/cast/list");
                return "msg";
            }
        }
        Map<String, String[]> params = request.getParameterMap();
        Cast cast = new Cast();
        if (fileName != null) {
            cast.setCastProfileImg(fileName);
        }
        BeanUtils.populate(cast, params);
        if (castService.updateById(cast)) {
            modelMap.addAttribute("msg", "更新成功！");
            modelMap.addAttribute("url", "/admin/cast/list");
            return "msg";
        }
        modelMap.addAttribute("msg", "更新失败！");
        modelMap.addAttribute("url", "/admin/cast/list");
        return "msg";
    }

    @GetMapping("delete")
    public String deleteCast(@RequestParam("id") Integer id, ModelMap modelMap) {
        try {
            if (castService.removeById(id)) {
                modelMap.addAttribute("msg", "删除成功！");
                modelMap.addAttribute("url", "/admin/cast/list");
                return "msg";
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("msg", "无法删除！当前影片与其他部分存在关系！");
            modelMap.addAttribute("url", "/admin/cast/list");
            return "msg";
        }
        modelMap.addAttribute("msg", "删除失败！");
        modelMap.addAttribute("url", "/admin/cast/list");
        return "msg";
    }
}
