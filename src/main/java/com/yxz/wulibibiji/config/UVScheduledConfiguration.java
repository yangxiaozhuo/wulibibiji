package com.yxz.wulibibiji.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.yxz.wulibibiji.entity.Uvcount;
import com.yxz.wulibibiji.service.UvcountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

import static com.yxz.wulibibiji.utils.RedisConstants.SYSTEM_DAY_VISIT;

/**
 * @author yangxiaozhuo
 * @date 2023/01/12
 */
@Configuration
@EnableScheduling   //开启定时任务
public class UVScheduledConfiguration {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UvcountService uvcountService;

    //添加定时任务  每天的23点  新增第二天的redis的key值
    @Scheduled(cron = "0 0 23 * * ?")
    private void flashRedisKey() {
        String tomorrow = DateUtil.format(DateUtil.tomorrow(), "yyyy-MM-dd");
        String key = SYSTEM_DAY_VISIT + tomorrow;
        stringRedisTemplate.opsForValue().set(key, "0");
    }

    //添加定时任务  每天的2点  记录前一天的访问量到mysql，并更新mysql的总访问量
    @Scheduled(cron = "0 0 2 * * ?")
    private void updateLastDay() {
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
