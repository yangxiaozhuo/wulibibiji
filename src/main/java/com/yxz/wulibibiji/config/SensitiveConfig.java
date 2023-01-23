package com.yxz.wulibibiji.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.dfa.SensitiveUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author yangxiaozhuo
 * @date 2023/01/14
 */
@Configuration
public class SensitiveConfig {
    @Value("${sensitive.filename}")
    private void flashRedisKey(String filename) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource("static/" + filename);
        List<String> list = FileUtil.readUtf8Lines(resource);
        SensitiveUtil.init(list);
    }
}
