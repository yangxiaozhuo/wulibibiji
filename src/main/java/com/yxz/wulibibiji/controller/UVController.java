package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.LoginFormDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.service.UserService;
import com.yxz.wulibibiji.service.UvcountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangxiaozhuo
 * @date 2023/01/13
 */
@Api(tags = "浏览量相关接口")
@RestController
@RequestMapping("/uv")
public class UVController {

    @Resource
    private UvcountService uvcountService;

    @ApiOperation(value = "获得平台当日浏览量")
    @GetMapping("/getDay")
    public Result getDay() {
        return uvcountService.getDay();
    }

    @ApiOperation(value = "获得平台总浏览量")
    @GetMapping("/getAll")
    public Result getAll() {
        return uvcountService.getAll();
    }
}
