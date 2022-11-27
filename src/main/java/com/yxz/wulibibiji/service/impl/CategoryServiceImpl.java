package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Category;
import com.yxz.wulibibiji.mapper.CategoryMapper;
import com.yxz.wulibibiji.service.CategoryService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.yxz.wulibibiji.utils.RedisConstants.ARTICLE_CATEGORY_NAME;

/**
 * @author Yang
 * @description 针对表【category】的数据库操作Service实现
 * @createDate 2022-11-18 22:24:23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result getCategoryList() {
        String key = ARTICLE_CATEGORY_NAME;
        String cateforyCache = stringRedisTemplate.opsForValue().get(key);
        if (!StrUtil.isBlank(cateforyCache)) {
            List<Category> categories = JSONUtil.toList(cateforyCache, Category.class);
            return Result.ok(categories);
        }
        List<Category> list = this.list();
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(list));
        return Result.ok(list);
    }

    @Override
    public Result getCategoryById(Integer id) {
        String key = ARTICLE_CATEGORY_NAME;
        String cateforyCache = stringRedisTemplate.opsForValue().get(key);
        List<Category> list;
        if (StrUtil.isBlank(cateforyCache)) {
            list = this.list();
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(list));
        } else {
            list = JSONUtil.toList(cateforyCache, Category.class);
        }
        for (Category category : list) {
            if (category.getCategoryId().equals(id)) {
                return Result.ok(category.getCategoryName());
            }
        }
        return Result.fail("没有这个分类");
    }
}




