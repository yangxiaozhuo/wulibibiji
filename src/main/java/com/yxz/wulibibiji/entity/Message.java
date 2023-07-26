package com.yxz.wulibibiji.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName message
 */
@TableName(value = "message")
public class Message implements Serializable {
    /**
     * 主键，关注主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 发送消息用户id
     */
    private String fromId;

    /**
     * 接收消息用户id
     */
    private String toId;

    /**
     * 会话id
     */
    private String conversionId;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态，0：未读 1:已读 2：撤回
     */
    private Integer status;

    /**
     * 发送时间
     */
    private Date createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private String fromAvatar;
    @TableField(exist = false)
    private String fromNickname;
    @TableField(exist = false)
    private String toAvatar;
    @TableField(exist = false)
    private String toNickname;
    @TableField(exist = false)
    private String unread;

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getFromNickname() {
        return fromNickname;
    }

    public void setFromNickname(String fromNickname) {
        this.fromNickname = fromNickname;
    }

    public String getToAvatar() {
        return toAvatar;
    }

    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar;
    }

    public String getToNickname() {
        return toNickname;
    }

    public void setToNickname(String toNickname) {
        this.toNickname = toNickname;
    }

    public String getUnread() {
        return unread;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }

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
     * 发送消息用户id
     */
    public String getFromId() {
        return fromId;
    }

    /**
     * 发送消息用户id
     */
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    /**
     * 接收消息用户id
     */
    public String getToId() {
        return toId;
    }

    /**
     * 接收消息用户id
     */
    public void setToId(String toId) {
        this.toId = toId;
    }

    /**
     * 会话id
     */
    public String getConversionId() {
        return conversionId;
    }

    /**
     * 会话id
     */
    public void setConversionId(String conversionId) {
        this.conversionId = conversionId;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 状态，0：未读 1:已读 2：撤回
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态，0：未读 1:已读 2：撤回
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 发送时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 发送时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    public Message() {
    }

    public Message(Long id, String fromId, String toId, String conversionId, String content, Integer status, Date createdTime) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.conversionId = conversionId;
        this.content = content;
        this.status = status;
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
        Message other = (Message) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getFromId() == null ? other.getFromId() == null : this.getFromId().equals(other.getFromId()))
                && (this.getToId() == null ? other.getToId() == null : this.getToId().equals(other.getToId()))
                && (this.getConversionId() == null ? other.getConversionId() == null : this.getConversionId().equals(other.getConversionId()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFromId() == null) ? 0 : getFromId().hashCode());
        result = prime * result + ((getToId() == null) ? 0 : getToId().hashCode());
        result = prime * result + ((getConversionId() == null) ? 0 : getConversionId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", fromId=").append(fromId);
        sb.append(", toId=").append(toId);
        sb.append(", conversionId=").append(conversionId);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}