package com.yxz.wulibibiji.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Category;

/**
 * @author Yang
 * @description 针对表【category】的数据库操作Service
 * @createDate 2022-11-18 22:24:23
 */
public interface CategoryService extends IService<Category> {

    Result getCategoryList();

    Result getCategoryById(Integer id);
}
