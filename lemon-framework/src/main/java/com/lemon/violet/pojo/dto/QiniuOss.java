package com.lemon.violet.pojo.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "qiniuyun.oss")
public class QiniuOss {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domain;
}
