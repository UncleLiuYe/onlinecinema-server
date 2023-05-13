package com.liuyetech.onlinecinemamanager.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyetech.onlinecinemamanager.domain.*;
import com.liuyetech.onlinecinemamanager.entity.CastCrewEntity;
import com.liuyetech.onlinecinemamanager.entity.MovieEntity;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/movie")
public class MovieController {
    @Autowired
    private MovieCastService movieCastService;

    @Autowired
    private MovieCrewService movieCrewService;

    @Autowired
    private CastService castService;

    @Autowired
    private CrewService crewService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OSSClient ossClient;

    @GetMapping("list")
    public R<List<Movie>> getAllMovie(Integer cid) {
        if (cid == null) {
            return R.success(movieService.list(null));
        } else {
            QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
            movieQueryWrapper.eq("movie_type", cid);
            movieQueryWrapper.le("movie_release_time", LocalDate.now());
            return R.success(movieService.list(movieQueryWrapper));
        }
    }

    @GetMapping("{mid}")
    public R<Movie> getMovieInfoByMid(@PathVariable("mid") Integer mid) {
        if (mid == null) {
            return R.fail("参数错误");
        }
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("movie_id", mid);
        return R.success(movieService.getOne(queryWrapper));
    }

    @GetMapping("type/{tid}")
    public R<List<Movie>> getMovieByTpye(@PathVariable("tid") Integer tid) {
        if (tid == null) {
            return R.fail("非法参数");
        }
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("movie_type", tid);
        return R.success(movieService.list(queryWrapper));
    }

    @GetMapping("cast/{mid}")
    public R<List<Cast>> getMovieCast(@PathVariable("mid") Integer mid) {
        if (mid == null) {
            return R.fail("参数错误");
        }
        QueryWrapper<MovieCast> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("movie_cast_movie_id", mid);
        return R.success(movieCastService.list(queryWrapper).stream().map(MovieCast::getCast).collect(Collectors.toList()));
    }

    @GetMapping("crew/{mid}")
    public R<List<Crew>> getMovieCrew(@PathVariable("mid") Integer mid) {
        if (mid == null) {
            return R.fail("参数错误");
        }
        QueryWrapper<MovieCrew> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("movie_crew_movie_id", mid);
        return R.success(movieCrewService.list(queryWrapper).stream().map(MovieCrew::getCrew).collect(Collectors.toList()));
    }

    @GetMapping("insert")
    public String insert(String mid) {
        String lang = "zh-CN";
        String apiKey = "104b4d668774615ed7603ade245521a9";
        String url = "http://api.themoviedb.org/3/movie/%s?api_key=%s&language=%s";
        String finalUrl = url.formatted(mid, apiKey, lang);
        ResponseEntity<MovieEntity> responseEntity = restTemplate.getForEntity(finalUrl, MovieEntity.class);
        if (responseEntity.getStatusCodeValue() != 200) {
            return "fail";
        }
        MovieEntity moviee = responseEntity.getBody();
        Movie movie = new Movie();
        movie.setMovieName(moviee.getTitle());
        movie.setMovieOverview(moviee.getOverview());
        String mLang = moviee.getOriginalLanguage();
        switch (mLang) {
            case "en" -> movie.setMovieLang("英语");
            case "zh" -> movie.setMovieLang("普通话");
            case "ja" -> movie.setMovieLang("日语");
            case "ko" -> movie.setMovieLang("韩语");
            default -> movie.setMovieLang(mLang);
        }
        movie.setMovieTagline(moviee.getTagline() == null ? "" : moviee.getTagline());
        if (moviee.getProductionCountries().size() > 0) {
            String area = moviee.getProductionCountries().get(0).getIso31661();
            switch (area) {
                case "US" -> movie.setMovieArea("美国");
                case "CN" -> movie.setMovieArea("中国");
                case "JP" -> movie.setMovieArea("日本");
                case "KR" -> movie.setMovieArea("韩国");
                default -> movie.setMovieArea(area);
            }
        } else {
            movie.setMovieArea("");
        }
        movie.setMovieRuntime(moviee.getRuntime());
        movie.setMovieStatus(1);
        movie.setMovieReleaseTime(moviee.getReleaseDate());
        StringBuffer stringBuffer = new StringBuffer();
        moviee.getGenres().forEach(genresDTO -> stringBuffer.append(genresDTO.getName()).append(","));
        movie.setMovieGenres(stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1));
        movie.setMoviePrice(19.9);
        movie.setMovieType(0);
        movie.setMoviePoster(moviee.getPosterPath());
        downLoadImage(moviee.getPosterPath());
        movie.setMoviePreviewUrl(mid + "/" + mid + ".m3u8");
        movie.setMoviePlayUrl(mid + "/" + mid + ".m3u8");

        int mres = movieService.getBaseMapper().insert((movie));
        if (mres <= 0) {
            return "movie insert fail";
        }
        int nmid = movie.getMovieId();

        String castCrewurl = "http://api.themoviedb.org/3/movie/%s/credits?api_key=%s&language=%s";
        String finalCastCrewUrl = castCrewurl.formatted(mid, apiKey, lang);
        ResponseEntity<CastCrewEntity> castCrewEntityResponseEntity = restTemplate.getForEntity(finalCastCrewUrl, CastCrewEntity.class);
        if (castCrewEntityResponseEntity.getStatusCodeValue() != 200) {
            return "castCrewEntityResponseEntity fail";
        }
        CastCrewEntity castCrewEntity = castCrewEntityResponseEntity.getBody();
        int size = castCrewEntity.getCast().size();
        if (size < 5) {
            castCrewEntity.getCast().forEach(castDTO -> {
                if (castDTO.getProfilePath() != null) {
                    Cast cast = new Cast();
                    cast.setCastName(castDTO.getName());
                    cast.setCastProfileImg(castDTO.getProfilePath());
                    downLoadImage(castDTO.getProfilePath());
                    cast.setCastCharacter(castDTO.getCharacter());
                    castService.getBaseMapper().insert(cast);
                    MovieCast movieCast = new MovieCast();
                    movieCast.setMovieCastMovieId(nmid);
                    movieCast.setMovieCastCastId(cast.getCastId());
                    movieCastService.save(movieCast);
                }
            });
        } else {
            castCrewEntity.getCast().subList(0, 5).forEach(castDTO -> {
                if (castDTO.getProfilePath() != null) {
                    Cast cast = new Cast();
                    cast.setCastName(castDTO.getName());
                    cast.setCastProfileImg(castDTO.getProfilePath());
                    downLoadImage(castDTO.getProfilePath());
                    cast.setCastCharacter(castDTO.getCharacter());
                    castService.getBaseMapper().insert(cast);
                    MovieCast movieCast = new MovieCast();
                    movieCast.setMovieCastMovieId(nmid);
                    movieCast.setMovieCastCastId(cast.getCastId());
                    movieCastService.save(movieCast);
                }
            });
        }

        castCrewEntity.getCrew().forEach(crewDTO -> {
            if ("Director".equals(crewDTO.getJob())) {
                Crew crew = new Crew();
                crew.setCrewJob(crewDTO.getJob());
                crew.setCrewProfileImg(crewDTO.getProfilePath().toString());
                downLoadImage(crewDTO.getProfilePath().toString());
                crew.setCrewName(crewDTO.getName());
                crewService.getBaseMapper().insert(crew);
                MovieCrew movieCrew = new MovieCrew();
                movieCrew.setMovieCrewMovieId(nmid);
                movieCrew.setMovieCrewCrewId(crew.getCrewId());
                movieCrewService.save(movieCrew);
            }
        });
        return "ok";
    }

    @GetMapping("doUpdate")
    public String doUpdate() {
        List<Movie> movies = movieService.list();
        for (Movie movie : movies) {
            String movieId = String.valueOf(movie.getMovieId());
            //10000 12
            movie.setMoviePreviewUrl("1" + "0".repeat((4 - movieId.length())) + movieId + "preview.mp4");
            movie.setMoviePlayUrl("1" + "0".repeat((4 - movieId.length())) + movieId + ".m3u8");
            movieService.updateById(movie);
        }
        return "ok";
    }

    private void downLoadImage(String url) {
        String finalUrl = "http://image.tmdb.org/t/p/w500/" + url;
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(finalUrl, HttpMethod.GET, null, byte[].class);
        //获取entity中的数据
        byte[] body = responseEntity.getBody();
        String key = "image/" + url.replace("/", "");
        PutObjectRequest putObjectRequest = new PutObjectRequest("onlinecinema", key, new ByteArrayInputStream(body));
        putObjectRequest.setProcess("true");
        PutObjectResult result = ossClient.putObject(putObjectRequest);
        if (!result.getResponse().isSuccessful()) {
            log.info("{}:上传失败", url);
        }
    }
}