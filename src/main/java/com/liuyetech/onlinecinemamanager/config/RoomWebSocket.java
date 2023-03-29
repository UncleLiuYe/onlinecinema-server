package com.liuyetech.onlinecinemamanager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuyetech.onlinecinemamanager.domain.Msg;
import com.liuyetech.onlinecinemamanager.domain.RoomBasicInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@ServerEndpoint("/api/v1/room/{roomId}")
@Component
public class RoomWebSocket {
    private static StringRedisTemplate redisTemplate;
    private static final ConcurrentHashMap<String, ArrayList<Session>> webSocketConcurrentHashMap = new ConcurrentHashMap<>();

    public static final ObjectMapper objectMapper = new ObjectMapper();
    private Session session;
    private String roomId;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        redisTemplate = stringRedisTemplate;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") String roomId) {
        this.session = session;
        this.roomId = roomId;
        if (webSocketConcurrentHashMap.get(roomId) == null) {
            webSocketConcurrentHashMap.put(roomId, new ArrayList<>());
            webSocketConcurrentHashMap.get(roomId).add(session);
        } else {
            webSocketConcurrentHashMap.get(roomId).add(session);
        }
        log.info("{}:{}加入了", roomId, session.getId());
        log.info("{}目前人数:{}", roomId, webSocketConcurrentHashMap.get(roomId).size());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("来自客户端{},用户:{},消息:{}", roomId, session.getId(), message);
        String[] messageArr = message.split("\\|");
        if ("join".equals(messageArr[0]) && redisTemplate.hasKey("room:" + roomId) == Boolean.TRUE) {
            log.info("收到加入{}", messageArr[1]);
            Msg msg = objectMapper.readValue(messageArr[1], Msg.class);
            msg.setType(Msg.TYPE_JOIN);
            RoomBasicInfo roomBasicInfo = new ObjectMapper().readValue(redisTemplate.opsForValue().get("room:" + roomId), RoomBasicInfo.class);
            roomBasicInfo.getRoomVips().add(msg.getRoomVip());
            redisTemplate.opsForValue().set("room:" + roomId, objectMapper.writeValueAsString(roomBasicInfo));
            sendInfo(roomId, "join|" + objectMapper.writeValueAsString(msg));
        }
        if ("exit".equals(messageArr[0])) {
            log.info("收到退出{}", messageArr[1]);
            Msg msg = objectMapper.readValue(messageArr[1], Msg.class);
            msg.setType(Msg.TYPE_EXIT);
            RoomBasicInfo roomBasicInfo = new ObjectMapper().readValue(redisTemplate.opsForValue().get("room:" + roomId), RoomBasicInfo.class);
            roomBasicInfo.getRoomVips().remove(msg.getRoomVip());
            redisTemplate.opsForValue().set("room:" + roomId, objectMapper.writeValueAsString(roomBasicInfo));
            sendInfo(roomId, "exit|" + objectMapper.writeValueAsString(msg));
        }
        if ("msg".equals(messageArr[0]) && redisTemplate.hasKey("room:" + roomId) == Boolean.TRUE) {
            log.info("收到信息{}", messageArr[1]);
            Msg msg = objectMapper.readValue(messageArr[1], Msg.class);
            msg.setType(Msg.TYPE_RECEIVED);
            sendInfo(roomId, "msg|" + objectMapper.writeValueAsString(msg));
        }
        if ("pos".equals(message) && redisTemplate.hasKey("room:" + roomId) == Boolean.TRUE) {
            sendInfo(roomId, redisTemplate.getExpire("room:" + roomId, TimeUnit.MILLISECONDS).toString());
        }
        if ("getPlayPos".equals(messageArr[0])) {
            if (redisTemplate.hasKey("room:" + roomId) == Boolean.TRUE) {
                sendMessage("getPlayPos|" + redisTemplate.getExpire("room:" + roomId).toString());
            }
        }
    }

    @OnClose
    public void onClose() {
        log.info("onclose");
        log.info("{}:{}离开了", roomId, session.getId());
        webSocketConcurrentHashMap.get(roomId).remove(session);
        log.info("{}目前人数:{}", roomId, webSocketConcurrentHashMap.get(roomId).size());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户{}错误:" + session.getId() + ",原因:" + error.getMessage());
    }

    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void sendInfo(String roomId, String info) {
        List<Session> sessions = webSocketConcurrentHashMap.get(roomId);
        log.info("{}", sessions.size());
        if (sessions != null && !sessions.isEmpty()) {
            for (Session session : sessions) {
                try {
                    log.info("发送给{}:{}", session.getId(), info);
                    session.getBasicRemote().sendText(info);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
