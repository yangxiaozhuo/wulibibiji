package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.service.FirstcommentService;
import com.yxz.wulibibiji.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangxiaozhuo
 * @date 2023/01/11
 */
@Api(tags = "关注相关接口")
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Resource
    private FollowService followService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "目标用户id", dataType = "String", required = true),
            @ApiImplicitParam(name = "isFollow", value = "是否关注", dataType = "boolean", required = true)
    })
    @ApiOperation(value = "关注或者取关目标用户", notes = "如果是取消关注，isFollow参数传入false，如果是关注，isFollow传入true")
    @PutMapping("/{userId}/{isFollow}")
    public Result likeBlog(@PathVariable("userId") String userId, @PathVariable("isFollow") boolean isFollow) {
        return followService.follow(userId, isFollow);
    }


    @ApiImplicitParam(name = "userId", value = "一级评论id", dataType = "Integer", required = true)
    @ApiOperation(value = "查询是否已经关注对方用户")
    @GetMapping("/or/not/{userId}")
    public Result likeBlog(@PathVariable("userId") String userId) {
        return followService.isFollowed(userId);
    }
}
