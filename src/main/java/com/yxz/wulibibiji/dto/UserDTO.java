package com.yxz.wulibibiji.dto;

public class UserDTO {
    private String email;
    private String nickName;
    private String avatar;
    private Integer sex;

    public UserDTO() {
    }

    public UserDTO(String email, String nickName, String avatar, Integer sex) {
        this.email = email;
        this.nickName = nickName;
        this.avatar = avatar;
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
