package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.LoginFormDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.User;
import com.yxz.wulibibiji.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yangxiaozhuo
 * @date 2022/11/18
 */

@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiImplicitParam(name = "loginFormDTO", value = "用户注册对象", dataType = "LoginFormDTO", required = true)
    @ApiOperation(value = "注册用户", notes = "用户密码长度必须是大于6，只允许出现数字和字母")
    @PostMapping("/create")
    public Result create(@RequestBody LoginFormDTO loginFormDTO) {
        return userService.create(loginFormDTO);
    }

    @ApiImplicitParam(name = "email", value = "教育邮箱地址", required = true)
    @ApiOperation(value = "教育邮箱发送验证码", notes = "给教育邮箱发送验证码，前端验证一下邮箱:长度为18位并且以@whut.edu.cn结尾")
    @PostMapping("/sentCode")
    public Result sentCode(@RequestParam("email") String email) {
        return userService.sentCode(email);
    }

    @ApiImplicitParam(name = "loginForm", value = "用户登录对象", dataType = "LoginFormDTO", required = true)
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginForm) {
        return userService.login(loginForm);
    }

    @ApiImplicitParam(name = "User", value = "用户User对象", dataType = "User", required = false)
    @ApiOperation(value = "编辑用户信息", notes = "需要传入nickname(昵称),sex(性别)")
    @PostMapping("/edit")
    public Result edit(@RequestBody User user) {
        return userService.edit(user);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "原密码", required = true),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true)
    })
    @ApiOperation(value = "修改密码", notes = "用户密码长度必须是大于6，只允许出现数字和字母")
    @PostMapping("/editPassword")
    public Result editPassword(@RequestBody Map<String, String> data) {
        return userService.editPassword(data.get("oldPassword"), data.get("newPassword"));
    }

    @ApiImplicitParam(name = "file", value = "图片对象", dataType = "MultipartFile", required = true)
    @ApiOperation(value = "上传头像", notes = "需要前端做校验，图片后缀，大小（暂定不超过1m）")
    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return userService.uploadAvatar(file, request);
    }

    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", required = true)
    @ApiOperation(value = "通过id查用户信息", notes = "传入id（即email），返回头像地址，nickname等基本信息")
    @GetMapping("/quary/{userId}")
    public Result quaryUserInfo(@PathVariable("userId") String userId) {
        return userService.quaryUserInfo(userId);
    }

    @ApiOperation(value = "查询用户是否登录", notes = "不需要参数,传入token即可。返回true || false")
    @GetMapping("/isLogin")
    public Result isLogin() {
        return userService.isLogin();
    }

    @ApiOperation(value = "退出登录", notes = "不需传入参数，代token")
    @PutMapping("/logout")
    public Result logout() {
        return userService.logout();
    }

}
