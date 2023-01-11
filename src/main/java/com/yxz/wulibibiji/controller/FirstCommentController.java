package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.FirstcommentDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.service.FirstcommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangxiaozhuo
 * @date 2023/01/09
 */
@Api(tags = "一级评论相关接口")
@RestController
@RequestMapping("/firstComment")
public class FirstCommentController {
    @Resource
    private FirstcommentService firstcommentService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "查询的页码数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "articleId", value = "评论所属文章id", dataType = "Integer", required = true)
    })
    @ApiOperation(value = "根据发布时间查询文章评论", notes = "默认查询最新的十条数据")
    @GetMapping("/getNewList")
    public Result queryNewArticle(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                  @RequestParam(value = "articleId") Integer articleId) {
        return firstcommentService.queryNewFirstComment(current, articleId);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "查询的页码数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "articleId", value = "评论所属文章id", dataType = "Integer", required = true)
    })
    @ApiOperation(value = "根据点赞数查询文章评论", notes = "默认查询最热的十条数据")
    @GetMapping("/getHotList")
    public Result queryHotArticle(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                  @RequestParam(value = "articleId") Integer articleId) {
        return firstcommentService.queryHotFirstComment(current, articleId);
    }

    @ApiImplicitParam(name = "firstcommentDTO", value = "评论对象，包括所属文章id，和评论内容", dataType = "FirstcommentDTO", required = true)
    @ApiOperation(value = "新增评论", notes = "一级评论，评论内容长度小于等于255")
    @PostMapping("/create")
    public Result createFirstComment(@RequestBody FirstcommentDTO firstcommentDTO) {
        return firstcommentService.createFirstComment(firstcommentDTO);
    }

    @ApiImplicitParam(name = "id", value = "一级评论id", dataType = "Integer", required = true)
    @ApiOperation(value = "给评论点赞或取消")
    @PutMapping("/like/{id}")
    public Result likeBlog(@PathVariable("id") Long id) {
        return firstcommentService.likeFirstComment(id);
    }
}
