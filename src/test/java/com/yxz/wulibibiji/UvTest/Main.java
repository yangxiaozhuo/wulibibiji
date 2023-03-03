package com.yxz.wulibibiji.UvTest;

import com.yxz.wulibibiji.service.UvcountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author yangxiaozhuo
 * @date 2023/01/12
 */
@SpringBootTest
public class Main {

    @Resource
    private UvcountService uvcountService;

    @Test
    void updateLastDay() {
    }
}
