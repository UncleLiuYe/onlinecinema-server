package com.liuyetech.onlinecinemamanager.controller.admin;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyetech.onlinecinemamanager.domain.Crew;
import com.liuyetech.onlinecinemamanager.domain.MovieCrew;
import com.liuyetech.onlinecinemamanager.entity.MovieCrewVo;
import com.liuyetech.onlinecinemamanager.service.CrewService;
import com.liuyetech.onlinecinemamanager.service.MovieCrewService;
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
import java.util.function.Consumer;

@Slf4j
@Controller
@RequestMapping("admin/crew")
public class CrewController {

    @Autowired
    private CrewService crewService;

    @Autowired
    private MovieCrewService movieCrewService;

    @Autowired
    private OSSClient ossClient;

    @GetMapping("list")
    public String crewList(Integer currentPage, ModelMap modelMap) {
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }
        IPage<Crew> page = new Page<>(currentPage, 8);
        modelMap.addAttribute("crews", crewService.page(page));
        return "crew/list";
    }

    @GetMapping("addCrew")
    public String addCrewList(ModelMap modelMap) {
        modelMap.addAttribute("crews", crewService.list());
        return "crew/alist";
    }

    @PostMapping("search")
    public String searchCrew(@RequestParam("crewName") String crewName, ModelMap modelMap) {
        QueryWrapper<Crew> crewQueryWrapper = new QueryWrapper<>();
        crewQueryWrapper.like("crew_name", crewName);
        modelMap.addAttribute("crews", crewService.list(crewQueryWrapper));
        return "crew/alist";
    }

    @PostMapping("searchCrew")
    public String searchCast(@RequestParam("crewName") String crewName, Integer currentPage, ModelMap modelMap) {
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }
        IPage<Crew> page = new Page<>(currentPage, 8);
        QueryWrapper<Crew> crewQueryWrapper = new QueryWrapper<>();
        crewQueryWrapper.like("crew_name", crewName);
        modelMap.addAttribute("crews", crewService.page(page, crewQueryWrapper));
        return "crew/list";
    }

    @PostMapping("updateMovieCrews")
    @ResponseBody
    public Integer updateMovieCrews(@RequestBody MovieCrewVo movieCrewVo) {
        QueryWrapper<MovieCrew> movieCrewQueryWrapper = new QueryWrapper<>();
        movieCrewQueryWrapper.eq("movie_crew_movie_id", movieCrewVo.getMid());
        List<MovieCrew> movieCrews = movieCrewService.list(movieCrewQueryWrapper);
        movieCrews.forEach(new Consumer<MovieCrew>() {
            @Override
            public void accept(MovieCrew movieCrew) {
                log.info(movieCrews.toString());
            }
        });
        for (MovieCrew movieCrew : movieCrews) {
            movieCrewService.removeById(movieCrew);
        }
        movieCrewVo.getCrewsIds().forEach(s -> {
            MovieCrew movieCrew = new MovieCrew();
            movieCrew.setMovieCrewMovieId(movieCrewVo.getMid());
            movieCrew.setMovieCrewCrewId(Integer.parseInt(s));
            movieCrewService.save(movieCrew);
        });
        return 1;
    }


    @GetMapping("add")
    public String addCrewPage() {
        return "crew/add";
    }

    @PostMapping("add")
    public String addCrew(MultipartFile crewImg, HttpServletRequest request, ModelMap modelMap) throws InvocationTargetException, IllegalAccessException {
        if (crewImg == null || crewImg.isEmpty()) {
            modelMap.addAttribute("msg", "文件不能为空！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        try {
            boolean isExists = ossClient.doesObjectExist("onlinecinema", crewImg.getOriginalFilename());
            if (!isExists) {
                PutObjectRequest putObjectRequest = new PutObjectRequest("onlinecinema"
                        , "image/" + crewImg.getOriginalFilename(), crewImg.getInputStream());
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
        Crew crew = new Crew();
        crew.setCrewProfileImg("/" + crewImg.getOriginalFilename());
        BeanUtils.populate(crew, params);

        if (crewService.save(crew)) {
            modelMap.addAttribute("msg", "添加成功！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        modelMap.addAttribute("msg", "添加失败！");
        modelMap.addAttribute("url", "/admin/home");
        return "msg";
    }

    @GetMapping("update")
    public String updateCrewPage(@RequestParam("id") Integer id, ModelMap modelMap) {
        QueryWrapper<Crew> crewQueryWrapper = new QueryWrapper<>();
        crewQueryWrapper.eq("crew_id", id);
        modelMap.addAttribute("crew", crewService.getOne(crewQueryWrapper));
        return "crew/update";
    }

    @PostMapping("update")
    public String updateCrew(MultipartFile crewImg, HttpServletRequest request, ModelMap modelMap) throws InvocationTargetException, IllegalAccessException {
        String fileName = null;
        if (crewImg != null && crewImg.getSize() > 0) {
            try {
                boolean isExists = ossClient.doesObjectExist("onlinecinema", crewImg.getOriginalFilename());
                if (!isExists) {
                    PutObjectRequest putObjectRequest = new PutObjectRequest("onlinecinema"
                            , "image/" + crewImg.getOriginalFilename(), crewImg.getInputStream());
                    putObjectRequest.setProcess("true");
                    ossClient.putObject(putObjectRequest);
                }
                fileName = "/" + crewImg.getOriginalFilename();
            } catch (Exception e) {
                e.printStackTrace();
                modelMap.addAttribute("msg", "文件上传失败！");
                modelMap.addAttribute("url", "/admin/home");
                return "msg";
            }
        }
        Map<String, String[]> params = request.getParameterMap();
        Crew crew = new Crew();
        if (fileName != null) {
            crew.setCrewProfileImg(fileName);
        }
        BeanUtils.populate(crew, params);
        if (crewService.updateById(crew)) {
            modelMap.addAttribute("msg", "更新成功！");
            modelMap.addAttribute("url", "/admin/home");
            return "msg";
        }
        modelMap.addAttribute("msg", "更新失败！");
        modelMap.addAttribute("url", "/admin/home");
        return "msg";
    }

    @GetMapping("delete")
    public String deleteCrew(@RequestParam("id") Integer id, ModelMap modelMap) {
        try {
            if (crewService.removeById(id)) {
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
