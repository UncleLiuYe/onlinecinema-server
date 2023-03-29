package com.liuyetech.onlinecinemamanager.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyetech.onlinecinemamanager.domain.Order;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/pay")
public class PayController {
    @Value("${ali-pay.ali-pay-public-key}")
    private String aliPayPublicKey;
    @Autowired
    private OrderService orderService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AlipayClient alipayClient;
    @Value("${ali-pay.notify-url}")
    private String notifyUrl;

    @PostMapping("repay")
    public R<String> rePay(@RequestBody Order order) {

        if (Boolean.FALSE.equals(redisTemplate.hasKey("order:" + order.getTradeNo()))) {
            return R.fail("订单已超时");
        }

        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

        AlipayTradeAppPayModel alipayTradeAppPayModel = new AlipayTradeAppPayModel();

        alipayTradeAppPayModel.setOutTradeNo(order.getTradeNo());
        alipayTradeAppPayModel.setTotalAmount(order.getTotalAmount().toString());
        alipayTradeAppPayModel.setSubject(order.getOrderDetail().getMovie().getMovieName() + "-" + "电影票");
        alipayTradeAppPayModel.setProductCode("FAST_INSTANT_TRADE_PAY");

        request.setBizModel(alipayTradeAppPayModel);
        request.setNotifyUrl(notifyUrl);
        AlipayTradeAppPayResponse response;
        try {
            response = alipayClient.sdkExecute(request);
            log.info(response.getSubMsg());
            if (response.isSuccess()) {
                return R.success("订单创建成功", response.getBody());
            }
            return R.fail("创建订单失败");
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return R.fail("创建订单失败");
        }
    }

    @PostMapping("notify")
    public String notifyPay(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        log.info(params.toString());
        boolean signVerified;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, aliPayPublicKey, AlipayConstants.CHARSET_UTF8, AlipayConstants.SIGN_TYPE_RSA2);
            //验签成功
            if (signVerified) {
                log.info("验签成功");

                QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
                orderQueryWrapper.eq("trade_no", params.get("out_trade_no"));
                Order order = orderService.getOne(orderQueryWrapper);
                if (order != null && (order.getTotalAmount() == Double.parseDouble(params.get("total_amount")))) {
                    order.setAlipayTradeNo(params.get("trade_no"));
                    switch (params.get("trade_status")) {
                        case "WAIT_BUYER_PAY" -> order.setStatus(0);
                        case "TRADE_CLOSED" -> {
                            order.setStatus(1);
                            order.setUpdateTime(LocalDateTime.parse(params.get("gmt_close"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        }
                        case "TRADE_SUCCESS", "TRADE_FINISHED" -> {
                            order.setStatus(2);
                            order.setUpdateTime(LocalDateTime.parse(params.get("gmt_payment"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        }
                    }
                    redisTemplate.delete("order:" + order.getTradeNo());
                    orderService.updateById(order);
                }
                return "success";
            }
            //验签失败
            else {
                log.error("验签失败");
                return "failure";
            }
        } catch (AlipayApiException e) {
            log.error("验签异常");
            return "failure";
        }
    }
}
