package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.ArticleDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Article;
import com.yxz.wulibibiji.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangxiaozhuo
 * @date 2022/11/19
 */
@Api(tags = "文章相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @ApiImplicitParam(name = "current" ,value = "查询的页码数",dataType = "Integer",required = false)
    @ApiOperation(value = "首页最新文章",notes = "默认查询最新的十条数据")
    @GetMapping("/new")
    public Result queryNewArticle(@RequestParam(value = "current", defaultValue = "1") Integer current) {
        return articleService.queryNewArticle(current);
    }

    @ApiImplicitParam(name = "current" ,value = "查询的页码数",dataType = "Integer",required = false)
    @ApiOperation(value = "过去一个月点赞数最多的文章",notes = "默认查询点赞数最多的十条数据")
    @GetMapping("/hot")
    public Result queryHotArticleg(@RequestParam(value = "current", defaultValue = "1") Integer current) {
        return articleService.queryHotArticle(current);
    }

    @ApiImplicitParam(name = "articleDTO" ,value = "新增的文章对象",dataType = "ArticleDTO",required = true)
    @ApiOperation(value = "发布文章",notes = "默认查询点赞数最多的十条数据")
    @PostMapping("/create")
    public Result createArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.createArticle(articleDTO);
    }
}
