package com.yxz.wulibibiji.UvTest;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.yxz.wulibibiji.entity.Uvcount;
import com.yxz.wulibibiji.service.UvcountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import static com.yxz.wulibibiji.utils.RedisConstants.SYSTEM_DAY_VISIT;

/**
 * @author yangxiaozhuo
 * @date 2023/01/12
 */
@SpringBootTest
public class Main {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UvcountService uvcountService;

    @Test
    void updateLastDay() {
        String yesterday = DateUtil.format(DateUtil.yesterday(), "yyyy-MM-dd");
        String key = SYSTEM_DAY_VISIT + yesterday;
        String s = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isBlank(s)) {
            return;
        }
        int value = Integer.parseInt(s);
        Uvcount uvcount = new Uvcount(null, value, DateUtil.yesterday(), "");
        int retry = 3;
        while (retry-- > 0) {
            if (uvcountService.save(uvcount)) {
                break;
            }
        }
        stringRedisTemplate.delete(key);
        uvcountService.update().eq("id","1").setSql("count = count + " + value).update();
    }
}
