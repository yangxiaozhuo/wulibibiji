package com.yxz.wulibibiji.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.dfa.SensitiveUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author yangxiaozhuo
 * @date 2023/01/14
 */
@Configuration
public class SensitiveConfig {
    @Value("${sensitive.filename}")
    private void flashRedisKey(String filename) {
        List<String> list = FileUtil.readUtf8Lines("static/" + filename);
        SensitiveUtil.init(list);
    }
}
