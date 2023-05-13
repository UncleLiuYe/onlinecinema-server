package com.liuyetech.onlinecinemamanager.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.liuyetech.onlinecinemamanager.domain.Movie;
import com.liuyetech.onlinecinemamanager.domain.Order;
import com.liuyetech.onlinecinemamanager.domain.OrderDetail;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.service.MovieService;
import com.liuyetech.onlinecinemamanager.service.OrderDetailService;
import com.liuyetech.onlinecinemamanager.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private AlipayClient alipayClient;

    @Value("${ali-pay.notify-url}")
    private String notifyUrl;

    @Autowired
    private MovieService movieService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("create")
    public R<String> createOrder(@RequestBody Movie movie) throws JsonProcessingException {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

        AlipayTradeAppPayModel alipayTradeAppPayModel = new AlipayTradeAppPayModel();

        String orderNo = getRandomTradeNo();
        alipayTradeAppPayModel.setOutTradeNo(orderNo);
        alipayTradeAppPayModel.setTotalAmount(String.valueOf(movie.getMoviePrice()));
        alipayTradeAppPayModel.setSubject(movie.getMovieName() + "-" + "电影票");
        alipayTradeAppPayModel.setProductCode("FAST_INSTANT_TRADE_PAY");

        request.setBizModel(alipayTradeAppPayModel);
        request.setNotifyUrl(notifyUrl);
        AlipayTradeAppPayResponse response;
        try {
            response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                Order order = new Order();
                order.setTradeNo(orderNo);
                order.setTotalAmount(movie.getMoviePrice());
                order.setCreateTime(LocalDateTime.now());
                order.setUpdateTime(LocalDateTime.now());
                order.setStatus(0);
                log.info(StpUtil.getLoginId().toString());
                order.setUserId(Integer.valueOf(StpUtil.getLoginId().toString()));
                if (orderService.save(order)) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderId(order.getOrderId());
                    orderDetail.setPrice(movie.getMoviePrice());
                    orderDetail.setNum(1);
                    orderDetail.setMovieId(movie.getMovieId());
                    redisTemplate.opsForValue().set("order:" + orderNo, orderNo, 10L, TimeUnit.MINUTES);
                    if (orderDetailService.save(orderDetail)) {
                        return R.success("订单创建成功", response.getBody());
                    } else {
                        return R.fail("创建订单失败");
                    }
                }
                return R.fail("创建订单失败");
            }
            return R.fail("创建订单失败");
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return R.fail("创建订单失败");
        }
    }

    @GetMapping("list")
    public R<List<Order>> orderList(@RequestParam Integer uid) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("user_id", uid);
        try {
            List<Order> orders = orderService.list(orderQueryWrapper);
            for (Order order : orders) {
                QueryWrapper<OrderDetail> orderDetailQueryWrapper = new QueryWrapper<>();
                orderDetailQueryWrapper.eq("order_id", order.getOrderId());
                OrderDetail orderDetail = orderDetailService.getOne(orderDetailQueryWrapper);
                orderDetail.setMovie(movieService.getOne(new QueryWrapper<Movie>().eq("movie_id", orderDetail.getMovieId())));
                order.setOrderDetail(orderDetail);
            }
            return R.success("获取成功", orders);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("获取失败");
        }
    }

    public static String getRandomTradeNo() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDateTime.now().getYear()).append(LocalDateTime.now().getMonthValue()).append(LocalDateTime.now().getDayOfMonth());
        int hour = LocalTime.now().getHour();
        int minute = LocalTime.now().getMinute();
        int second = LocalTime.now().getSecond();
        if (hour < 10) {
            sb.append("0").append(hour);
        } else {
            sb.append(hour);
        }
        if (minute < 10) {
            sb.append("0").append(minute);
        } else {
            sb.append(minute);
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
