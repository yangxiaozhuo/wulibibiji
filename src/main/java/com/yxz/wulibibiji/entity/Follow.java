package com.yxz.wulibibiji.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName follow
 */
@TableName(value ="follow")
public class Follow implements Serializable {
    /**
     * 主键，关注主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 关注的用户id
     */
    private String followUserId;

    /**
     * 关注时间
     */
    private Date createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键，关注主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键，关注主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 关注的用户id
     */
    public String getFollowUserId() {
        return followUserId;
    }

    /**
     * 关注的用户id
     */
    public void setFollowUserId(String followUserId) {
        this.followUserId = followUserId;
    }

    /**
     * 关注时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 关注时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Follow() {
    }

    public Follow(Long id, String userId, String followUserId, Date createdTime) {
        this.id = id;
        this.userId = userId;
        this.followUserId = followUserId;
        this.createdTime = createdTime;
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
        Follow other = (Follow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getFollowUserId() == null ? other.getFollowUserId() == null : this.getFollowUserId().equals(other.getFollowUserId()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFollowUserId() == null) ? 0 : getFollowUserId().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", followUserId=").append(followUserId);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}