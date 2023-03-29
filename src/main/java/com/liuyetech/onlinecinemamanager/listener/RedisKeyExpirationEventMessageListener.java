package com.liuyetech.onlinecinemamanager.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyetech.onlinecinemamanager.domain.Order;
import com.liuyetech.onlinecinemamanager.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class RedisKeyExpirationEventMessageListener extends KeyExpirationEventMessageListener {
    @Autowired
    private OrderService orderService;

    public RedisKeyExpirationEventMessageListener(RedisMessageListenerContainer redisMessageListenerContainer) {
        super(redisMessageListenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expireKey = message.toString();
        if (expireKey.startsWith("order:")) {
            String[] orderInfo = expireKey.split(":");
            QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
            orderQueryWrapper.eq("trade_no", orderInfo[1]);
            Order order = orderService.getOne(orderQueryWrapper);
            if (order != null) {
                order.setStatus(1);
                order.setUpdateTime(LocalDateTime.now());
                orderService.updateById(order);
            }
        }
        log.info("Redis Key {} 过期了...", expireKey);
    }
}
