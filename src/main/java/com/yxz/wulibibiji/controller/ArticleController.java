package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.ArticleDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.service.ArticleService;
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

    @ApiImplicitParam(name = "current", value = "查询的页码数", dataType = "Integer", required = false)
    @ApiOperation(value = "首页最新文章", notes = "默认查询最新的十条数据")
    @GetMapping("/new")
    public Result queryNewArticle(@RequestParam(value = "current", defaultValue = "1") Integer current) {
        return articleService.queryNewArticle(current);
    }

    @ApiImplicitParam(name = "current", value = "查询的页码数", dataType = "Integer", required = false)
    @ApiOperation(value = "过去一个月点赞数最多的文章", notes = "默认查询点赞数最多的十条数据")
    @GetMapping("/hot")
    public Result queryHotArticleg(@RequestParam(value = "current", defaultValue = "1") Integer current) {
        return articleService.queryHotArticle(current);
    }

    @ApiImplicitParam(name = "articleDTO", value = "新增的文章对象", dataType = "ArticleDTO", required = true)
    @ApiOperation(value = "发布文章", notes = "返回文章的id，凭此id上传图片")
    @PostMapping("/create")
    public Result createArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.createArticle(articleDTO);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件列表", dataType = "List<MultipartFile>", required = true),
            @ApiImplicitParam(name = "articleId", value = "文章id", dataType = "Integer", required = true),
    })
    @ApiOperation(value = "上传文章图片", notes = "最多上传9张图片，大小限制10m以内")
    @PostMapping("/uploadImg")
    public Result uploadImg(@RequestParam("files") List<MultipartFile> files, @RequestParam("articleId") Integer id) {
        return articleService.uploadImg(files, id);
    }

    @ApiImplicitParam(name = "id", value = "文章id", dataType = "Integer", required = true)
    @ApiOperation(value = "给文章点赞或取消")
    @PutMapping("/like/{id}")
    public Result likeAriticle(@PathVariable("id") Long id) {
        return articleService.likeArticle(id);
    }

    @ApiImplicitParam(name = "id", value = "文章id", dataType = "Integer", required = true)
    @ApiOperation(value = "查询文章详细信息")
    @GetMapping("/detail/{id}")
    public Result detailAriticle(@PathVariable("id") Long id) {
        return articleService.detailAriticle(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "查询的页码数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "useId", value = "用户id", dataType = "String", required = true)
    })
    @ApiOperation(value = "查询某用户的所有文章")
    @GetMapping("/all")
    public Result allArticle(@RequestParam("useId") String useId, @RequestParam("current") Integer current) {
        return articleService.allArticle(useId, current);
    }
}
