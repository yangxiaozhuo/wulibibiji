package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangxiaozhuo
 * @date 2022/11/25
 */
@Api(tags = "文章分类相关接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @ApiOperation(value = "查询分类列表")
    @GetMapping("/category")
    public Result getCategoryList() {
        return categoryService.getCategoryList();
    }

    @ApiOperation(value = "通过id查询分类")
    @GetMapping("/category/{id}")
    public Result getCategoryByid(@PathVariable(value = "id") Integer id) {
        return categoryService.getCategoryById(id);
    }
}
