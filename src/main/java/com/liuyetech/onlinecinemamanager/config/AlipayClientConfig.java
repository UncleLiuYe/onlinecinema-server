package com.liuyetech.onlinecinemamanager.config;

import com.alipay.api.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AlipayClientConfig {
    @Value("${ali-pay.app-id}")
    private String appId;
    @Value("${ali-pay.merchant-private-key}")
    private String merchantPrivateKey;
    @Value("${ali-pay.ali-pay-public-key}")
    private String aliPayPublicKey;
    @Value("${ali-pay.gateway-url}")
    private String gatewayUrl;

    @Bean
    public AlipayClient alipayClient() throws AlipayApiException {
        AlipayConfig config = new AlipayConfig();

        log.info(this.merchantPrivateKey);
        //设置appId
        config.setAppId(appId);
        //设置商户私钥
        config.setPrivateKey(merchantPrivateKey);
        //设置支付宝公钥
        config.setAlipayPublicKey(aliPayPublicKey);
        //设置支付宝网关
        config.setServerUrl(gatewayUrl);
        //设置请求格式,固定值json.
        config.setFormat(AlipayConstants.FORMAT_JSON);
        //设置字符集
        config.setCharset(AlipayConstants.CHARSET_UTF8);
        //设置签名类型
        config.setSignType(AlipayConstants.SIGN_TYPE_RSA2);
        return new DefaultAlipayClient(config);
    }
}
