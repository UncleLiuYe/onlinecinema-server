package com.liuyetech.onlinecinemamanager.controller.admin;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyetech.onlinecinemamanager.domain.Movie;
import com.liuyetech.onlinecinemamanager.domain.MovieCast;
import com.liuyetech.onlinecinemamanager.domain.MovieCrew;
import com.liuyetech.onlinecinemamanager.service.CategoryService;
import com.liuyetech.onlinecinemamanager.service.MovieCastService;
import com.liuyetech.onlinecinemamanager.service.MovieCrewService;
import com.liuyetech.onlinecinemamanager.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("admin/movie")
public class AdminMovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MovieCastService movieCastService;

    @Autowired
    private MovieCrewService movieCrewService;

    @Autowired
    private OSSClient ossClient;

    @GetMapping("list")
    public String movieList(Integer currentPage, ModelMap modelMap) {
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }
        IPage<Movie> page = new Page<>(currentPage, 8);
        modelMap.addAttribute("category", categoryService.list());
        modelMap.addAttribute("datas", movieService.page(page));
        return "movie/list";
    }

    @GetMapping("show")
    public String showMovie(@RequestParam("mid") Integer mid, ModelMap modelMap) {
        QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
        movieQueryWrapper.eq("movie_id", mid);

        QueryWrapper<MovieCast> movieCastQueryWrapper = new QueryWrapper<>();
        movieCastQueryWrapper.eq("movie_id", mid);

        QueryWrapper<MovieCrew> movieCrewQueryWrapper = new QueryWrapper<>();
        movieCrewQueryWrapper.eq("movie_id", mid);

        modelMap.addAttribute("casts", movieCastService.list(movieCastQueryWrapper));
        modelMap.addAttribute("crews", movieCrewService.list(movieCrewQueryWrapper));
        modelMap.addAttribute("movie", movieService.getOne(movieQueryWrapper));
        return "movie/show";
    }

    @GetMapping("update")
    public String updateMoviePage(@RequestParam("mid") Integer mid, ModelMap modelMap) {
        modelMap.addAttribute("category", categoryService.list());

        QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
        movieQueryWrapper.eq("movie_id", mid);

        QueryWrapper<MovieCast> movieCastQueryWrapper = new QueryWrapper<>();
        movieCastQueryWrapper.eq("movie_id", mid);

        QueryWrapper<MovieCrew> movieCrewQueryWrapper = new QueryWrapper<>();
        movieCrewQueryWrapper.eq("movie_id", mid);

        modelMap.addAttribute("casts", movieCastService.list(movieCastQueryWrapper));
        modelMap.addAttribute("crews", movieCrewService.list(movieCrewQueryWrapper));
        modelMap.addAttribute("movie", movieService.getOne(movieQueryWrapper));
        return "movie/update";
    }

    @PostMapping("update")
    public String updateMovie(MultipartFile moviePoster, HttpServletRequest request, ModelMap modelMap) throws InvocationTargetException, IllegalAccessException {
        String fileName = null;
        if (moviePoster != null && moviePoster.getSize() > 0) {
            try {
                boolean isExists = ossClient.doesObjectExist("onlinecinema", moviePoster.getOriginalFilename());
                if (!isExists) {
                    PutObjectRequest putObjectRequest = new PutObjectRequest("onlinecinema"
                            , "image/" + moviePoster.getOriginalFilename(), moviePoster.getInputStream());
                    putObjectRequest.setProcess("true");
                    ossClient.putObject(putObjectRequest);
                }
                fileName = "/" + moviePoster.getOriginalFilename();
            } catch (Exception e) {
                e.printStackTrace();
                modelMap.addAttribute("msg", "文件上传失败！");
                modelMap.addAttribute("url", "/admin/home");
                return "msg";
            }
        }
        Map<String, String[]> params = request.getParameterMap();
        Movie movie = new Movie();
        if (fileName != null) {
            movie.setMoviePoster(fileName);
        }
        BeanUtils.populate(movie, params);
        log.info(movie.toString());
        if (movieService.updateById(movie)) {
            modelMap.addAttribute("msg", "更新成功！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        modelMap.addAttribute("msg", "更新失败！");
        modelMap.addAttribute("url", "/admin/home");
        return "msg";
    }

    @GetMapping("add")
    public String addMoviePage(ModelMap modelMap) {
        modelMap.addAttribute("category", categoryService.list());
        return "movie/add";
    }

    @PostMapping("add")
    public String addMovie(MultipartFile moviePoster, HttpServletRequest request, ModelMap modelMap) throws InvocationTargetException, IllegalAccessException {
        if (moviePoster == null || moviePoster.isEmpty()) {
            modelMap.addAttribute("msg", "文件不能为空！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        try {
            boolean isExists = ossClient.doesObjectExist("onlinecinema", moviePoster.getOriginalFilename());
            if (!isExists) {
                PutObjectRequest putObjectRequest = new PutObjectRequest("onlinecinema"
                        , "image/" + moviePoster.getOriginalFilename(), moviePoster.getInputStream());
                putObjectRequest.setProcess("true");
                ossClient.putObject(putObjectRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("msg", "文件上传失败！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        Map<String, String[]> params = request.getParameterMap();
        Movie movie = new Movie();
        movie.setMovieStatus(1);
        movie.setMoviePreviewUrl("");
        movie.setMoviePlayUrl("");
        movie.setMoviePoster("/" + moviePoster.getOriginalFilename());
        BeanUtils.populate(movie, params);
        log.info(movie.toString());
        if (movieService.save(movie)) {
            modelMap.addAttribute("msg", "添加成功！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        modelMap.addAttribute("msg", "添加失败！");
        modelMap.addAttribute("url", "/admin/home");
        return "msg";
    }

    @PostMapping("search")
    public String searchMovie(String mname, Integer mtype, Integer status, Integer currentPage, ModelMap modelMap) {
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }
        QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(mname)) {
            movieQueryWrapper.like("movie_name", mname);
        }
        if (mtype != null && mtype != 0) {
            movieQueryWrapper.eq("movie_type", mtype);
        }
        if (status != null && status != 3) {
            movieQueryWrapper.eq("movie_status", status);
        }
        IPage<Movie> page = new Page<>(currentPage, 8);
        modelMap.addAttribute("category", categoryService.list());
        modelMap.addAttribute("datas", movieService.page(page, movieQueryWrapper));
        return "movie/list";
    }

    @GetMapping("delete")
    public String deleteMovie(@RequestParam("mid") Integer mid, ModelMap modelMap) {
        try {
            if (movieService.removeById(mid)) {
                modelMap.addAttribute("msg", "删除成功！");
                modelMap.addAttribute("url", "/admin/home");
                return "msg";
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("msg", "无法删除！当前影片与其他部分存在关系！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        modelMap.addAttribute("msg", "删除失败！");
        modelMap.addAttribute("url", "/admin/home");
        return "msg";
    }
}