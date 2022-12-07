package com.yxz.wulibibiji.config;

import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxiaozhuo
 * @date 2022/12/07
 */
@Configuration
public class QiniuCloudConfiguration {
    /**
     * 华南机房
     *
     * @return
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {
        return new com.qiniu.storage.Configuration(Region.huadong());
    }

    /**
     * 构建一个上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiniuConfig());
    }

    @Value("${qiniu.AccessKey}")
    private String accessKey;

    @Value("${qiniu.SecretKey}")
    private String secretKey;

    /**
     * 认证信息
     *
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiniuConfig());
    }
}
