package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.dto.SonCommentDTO;
import com.yxz.wulibibiji.service.SoncommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangxiaozhuo
 * @date 2023/01/10
 */
@Api(tags = "二级评论相关接口")
@RestController
@RequestMapping("/sonComment")
public class SonCommentController {
    @Resource
    private SoncommentService soncommentService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "查询的页码数", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "firstCommentId", value = "父级评论id", dataType = "Integer", required = true)
    })
    @ApiOperation(value = "查询父级评论对应的子级评论", notes = "默认查询最新的十条数据")
    @GetMapping("/getList")
    public Result querySonComment(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                  @RequestParam(value = "firstCommentId") Integer firstCommentId) {
        return soncommentService.querySonComment(current, firstCommentId);
    }

    @ApiImplicitParam(name = "id", value = "二级评论id", dataType = "Integer", required = true)
    @ApiOperation(value = "给二级评论点赞或取消")
    @PutMapping("/like/{id}")
    public Result likeSonComment(@PathVariable("id") Long id) {
        return soncommentService.likeSonComment(id);
    }

    @ApiImplicitParam(name = "soncommentDTO", value = "评论对象，包括所属文章id，和评论内容", dataType = "FirstcommentDTO", required = true)
    @ApiOperation(value = "新增二级评论", notes = "二级评论，评论内容长度小于等于255")
    @PostMapping("/create")
    public Result createSonComment(@RequestBody SonCommentDTO soncommentDTO) {
        return soncommentService.createSonComment(soncommentDTO);
    }
}
