package com.yxz.wulibibiji.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.entity.Category;
import com.yxz.wulibibiji.service.CategoryService;
import com.yxz.wulibibiji.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Yang
* @description 针对表【category】的数据库操作Service实现
* @createDate 2022-11-18 22:24:23
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{
}




