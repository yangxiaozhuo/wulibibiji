package com.yxz.wulibibiji.service;

import com.yxz.wulibibiji.dto.LoginFormDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
* @author Yang
* @description 针对表【user】的数据库操作Service
* @createDate 2022-11-18 22:24:23
*/
public interface UserService extends IService<User> {


    public Result sentCode(String email);

    public Result create(LoginFormDTO loginFormDTO);

    Result login(LoginFormDTO loginForm, HttpSession session);
}
