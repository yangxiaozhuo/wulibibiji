package com.yxz.wulibibiji.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.wulibibiji.dto.LoginFormDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Yang
 * @description 针对表【user】的数据库操作Service
 * @createDate 2022-11-18 22:24:23
 */
public interface UserService extends IService<User> {


    Result sentCode(String email);

    Result create(LoginFormDTO loginFormDTO);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result edit(User user);

    Result uploadAvatar(MultipartFile file, HttpServletRequest request);

    Result editPassword(String oldPassword, String newPassword);

    Result quaryUserInfo(String userId);

    Result logout();
}
