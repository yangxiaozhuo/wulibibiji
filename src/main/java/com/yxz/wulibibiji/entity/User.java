package com.yxz.wulibibiji.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user
 */
@TableName(value = "user")
public class User implements Serializable {
    /**
     * 学校邮箱
     */
    @TableId
    private String userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 加强密码
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户性别
     */
    private Integer sex;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 学校邮箱
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 学校邮箱
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 加强密码
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 加强密码
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 用户性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 用户性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public User() {
    }

    public User(String userId, String nickname, String password, String salt, String avatar, Date createTime, Integer sex) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
        this.avatar = avatar;
        this.createTime = createTime;
        this.sex = sex;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
                && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", nickname=").append(nickname);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", avatar=").append(avatar);
        sb.append(", createTime=").append(createTime);
        sb.append(", sex=").append(sex);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}