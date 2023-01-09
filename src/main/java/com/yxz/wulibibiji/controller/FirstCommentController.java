package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.FirstcommentDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Firstcomment;
import com.yxz.wulibibiji.service.ArticleService;
import com.yxz.wulibibiji.service.FirstcommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangxiaozhuo
 * @date 2023/01/09
 */
@Api("一级评论相关接口")
@RestController
@RequestMapping("/firstComment")
public class FirstCommentController {
    @Resource
    private FirstcommentService firstcommentService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "查询的页码数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "articleId", value = "评论所属文章id", dataType = "Integer", required = true)
    })
    @ApiOperation(value = "查询文章评论", notes = "默认查询最新的十条数据")
    @GetMapping("/getList")
    public Result queryNewArticle(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                  @RequestParam(value = "articleId") Integer articleId) {
        return firstcommentService.queryFirstComment(current, articleId);
    }

    @ApiImplicitParam(name = "firstcommentDTO", value = "评论对象，包括所属文章id，和评论内容", dataType = "FirstcommentDTO", required = true)
    @ApiOperation(value = "新增评论", notes = "一级评论，评论内容长度小于等于255")
    @PostMapping("/create")
    public Result createArticle(@RequestBody FirstcommentDTO firstcommentDTO) {
        return firstcommentService.createArticle(firstcommentDTO);
    }

    @ApiImplicitParam(name = "id", value = "一级评论id", dataType = "Integer", required = true)
    @ApiOperation(value = "给文章点赞或取消")
    @PutMapping("/like/{id}")
    public Result likeBlog(@PathVariable("id") Long id) {
        return firstcommentService.likeFirstComment(id);
    }
}
