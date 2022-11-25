package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.LoginFormDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.User;
import com.yxz.wulibibiji.service.UserService;
import com.yxz.wulibibiji.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.annotation.REntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author yangxiaozhuo
 * @date 2022/11/18
 */

@Api(tags = "登录注册")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiImplicitParam(name = "loginFormDTO" ,value = "用户注册对象",dataType = "LoginFormDTO",required = true)
    @ApiOperation(value = "注册用户",notes = "用户密码长度必须是大于6，只允许出现数字和字母")
    @PostMapping("/create")
    public Result create(@RequestBody LoginFormDTO loginFormDTO) {
        return userService.create(loginFormDTO);
    }

    @ApiImplicitParam(name = "email", value = "教育邮箱地址", required = true)
    @ApiOperation(value = "教育邮箱发送验证码",notes = "给教育邮箱发送验证码，前端验证一下邮箱:长度为18位并且以@whut.edu.cn结尾")
    @PostMapping("/sentCode")
    public Result create(@RequestParam("email") String email) {
        return userService.sentCode(email);
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "loginForm" ,value = "用户登录对象",dataType = "LoginFormDTO",required = true),
        @ApiImplicitParam(name = "session" ,value = "session对象",dataType = "HttpSession",required = true)
    })
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginForm, HttpSession session){
        return userService.login(loginForm, session);
    }

}
