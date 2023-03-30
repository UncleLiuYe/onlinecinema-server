package com.liuyetech.onlinecinemamanager.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliOSSConfig {
    @Value("${ali-oss.end-point}")
    private String endPoint;
    @Value("${ali-oss.bucket-name}")
    private String bucketName;
    @Value("${ali-oss.key-id}")
    private String keyId;
    @Value("${ali-oss.key-secret}")
    private String keySecret;

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(endPoint, new DefaultCredentialProvider(keyId, keySecret),
                new ClientConfiguration());
    }
}
