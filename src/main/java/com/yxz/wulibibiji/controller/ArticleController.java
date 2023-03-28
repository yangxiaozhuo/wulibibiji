package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.ArticleDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.EsArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private EsArticleService esArticleService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "分类id", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "current", value = "查询的页码数", dataType = "Integer", required = false)
    })
    @ApiOperation(value = "首页最新文章", notes = "默认查询最新的十条数据")
    @GetMapping("/new")
    public Result queryNewArticle(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                  @RequestParam(value = "category", defaultValue = "0") Integer category) {
        return articleService.queryNewArticle(current, category);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "分类id", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "current", value = "查询的页码数", dataType = "Integer", required = false)
    })
    @ApiOperation(value = "过去一个月点赞数最多的文章", notes = "默认查询点赞数最多的十条数据")
    @GetMapping("/hot")
    public Result queryHotArticle(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                  @RequestParam(value = "category", defaultValue = "0") Integer category) {
        return articleService.queryHotArticle(current, category);
    }

    @ApiImplicitParam(name = "articleDTO", value = "新增的文章对象", dataType = "ArticleDTO", required = true)
    @ApiOperation(value = "发布文章", notes = "直接上传所有信息")
    @PostMapping("/create")
    public Result createArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.createArticle(articleDTO);
    }

    @ApiImplicitParam(name = "id", value = "文章id", dataType = "Integer", required = true)
    @ApiOperation(value = "给文章点赞或取消")
    @PutMapping("/like/{id}")
    public Result likeArticle(@PathVariable("id") Long id) {
        return articleService.likeArticle(id);
    }

    @ApiImplicitParam(name = "id", value = "文章id", dataType = "Integer", required = true)
    @ApiOperation(value = "查询文章详细信息")
    @GetMapping("/detail/{id}")
    public Result detailArticle(@PathVariable("id") Long id) {
        return articleService.detailArticle(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "查询的页码数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "useId", value = "用户id", dataType = "String", required = true)
    })
    @ApiOperation(value = "查询某用户的所有文章")
    @GetMapping("/all")
    public Result allArticle(@RequestParam("useId") String useId, @RequestParam(value = "current", defaultValue = "1") Integer current) {
        return articleService.allArticle(useId, current);
    }

    @ApiImplicitParam(name = "key", value = "检索关键字", dataType = "Sting", required = false)
    @ApiOperation(value = "查询文章", notes = "返回文章简单信息和id")
    @GetMapping("/search")
    public Result search(@RequestParam("key") String key) {
        return esArticleService.search(key);
    }
}
