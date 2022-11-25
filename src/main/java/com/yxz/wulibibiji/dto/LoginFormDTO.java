package com.yxz.wulibibiji.dto;

import lombok.Data;

/**
 * @author yangxiaozhuo
 * @date 2022/11/19
 */
@Data
public class LoginFormDTO {
    private String email;
    private String code;
    private String password;
}

