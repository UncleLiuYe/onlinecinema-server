package com.liuyetech.onlinecinemamanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuyetech.onlinecinemamanager.domain.RoomBasicInfo;
import com.liuyetech.onlinecinemamanager.domain.RoomCreaterInfo;
import com.liuyetech.onlinecinemamanager.domain.RoomType;
import com.liuyetech.onlinecinemamanager.domain.VideoInfo;
import com.liuyetech.onlinecinemamanager.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("api/v1/room")
public class RoomController {
    public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    public static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) throws JsonProcessingException {
        RoomBasicInfo roomBasicInfo = new RoomBasicInfo();

        roomBasicInfo.setRoomType(RoomType.PUBLIC.getCode());
        roomBasicInfo.setRoomMaxSize(5);

        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setVideoId(10001L);
        videoInfo.setVideoName("犯罪都市2");
        videoInfo.setVideoPic("https://www.imgikzy.com/upload/vod/20221108-21/0899c19ae03f8dc0e35f7673700f876a.jpg");
        videoInfo.setVideoUrl("http://localhost:9999/10002.mp4");
        videoInfo.setVideoDuration(hourMinutesSecondtoLong(1, 46, 03));

        RoomCreaterInfo roomCreaterInfo = new RoomCreaterInfo();
        roomCreaterInfo.setUserId(10000L);
        roomCreaterInfo.setUserName("LiuYe");
        roomCreaterInfo.setUserAvator("https://qlogo3.store.qq.com/qzone/215159994/215159994/100?1669707442");

        roomBasicInfo.setVideoInfo(videoInfo);
        roomBasicInfo.setCreaterInfo(roomCreaterInfo);

        roomBasicInfo.setRoomVips(new ArrayList<>());

        log.info(objectMapper.writeValueAsString(roomBasicInfo));
    }

    @PostMapping("create")
    public String createRoom(@RequestBody RoomBasicInfo createInfo) {
        String roomName = getRandomString(5);
        createInfo.setRoomName(roomName);
        createInfo.setRoomCreateTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        try {
            redisTemplate.opsForValue().set("room:" + roomName, objectMapper.writeValueAsString(createInfo), createInfo.getVideoInfo().getVideoDuration(), TimeUnit.MILLISECONDS);
            return "ok";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @GetMapping("info/{roomId}")
    public R<?> info(@PathVariable("roomId") String roomId) {
        log.info(roomId);
        if (redisTemplate.hasKey("room:" + roomId) == Boolean.TRUE) {
            try {
                return R.success("获取成功", objectMapper.readValue(redisTemplate.opsForValue().get("room:" + roomId), RoomBasicInfo.class));
            } catch (JsonProcessingException e) {
                return R.fail("获取失败");
            }
        }
        return R.fail("获取失败");
    }

    @GetMapping("playDuration/{roomId}")
    public Long getPlayDuration(@PathVariable("roomId") String roomId) {
        if (redisTemplate.hasKey("room:" + roomId) == Boolean.TRUE) {
            return redisTemplate.getExpire("room:" + roomId, TimeUnit.MILLISECONDS);
        }
        return 0L;
    }

    @GetMapping("list")
    public R<?> listRoom() {
        Set<String> set = redisTemplate.keys("room:*");
        if (set != null) {
            try {
                List<RoomBasicInfo> roomBasicInfos = new ArrayList<>();
                for (String str : set) {
                    String json = redisTemplate.opsForValue().get(str);
                    RoomBasicInfo roomBasicInfo = objectMapper.readValue(json, RoomBasicInfo.class);
                    roomBasicInfos.add(roomBasicInfo);
                }
                return R.success("获取成功", roomBasicInfos);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return R.fail("获取失败");
    }

//    @GetMapping("info")
//    public String infoRoom(@RequestParam String roomName) {
//        if (redisTemplate.hasKey("room:" + roomName) == Boolean.TRUE) {
//            return redisTemplate.opsForValue().get("room:" + roomName);
//        }
//        return "啥也没有.";
//    }
//
//    @PostMapping("join")
//    public String joinRoom(@RequestBody RoomJoinInfo joinInfo) throws JsonProcessingException {
//        log.info(joinInfo.toString());
//        if (redisTemplate.hasKey("room:" + joinInfo.getRoomName()) == Boolean.TRUE) {
//            RoomInfo roomInfo = objectMapper.readValue(redisTemplate.opsForValue().get("room:" + joinInfo.getRoomName()), RoomInfo.class);
//            log.info(roomInfo.toString());
//            roomInfo.getRoomVips().add(joinInfo.getUserinfo().get(0));
//            redisTemplate.opsForValue().set("room:" + joinInfo.getRoomName(), objectMapper.writeValueAsString(roomInfo));
//            return joinInfo.getUserinfo().get(0).getUserName() + "加入了房间";
//        }
//        return "加入失败";
//    }

    public static String getRandomString(int size) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(CHARS[random.nextInt(62)]);
        }
        return sb.toString();
    }

    private static Long hourMinutesSecondtoLong(int hour, int min, int sec) {
        return (hour * 3600L + min * 60L + sec) * 1000;
    }
}
