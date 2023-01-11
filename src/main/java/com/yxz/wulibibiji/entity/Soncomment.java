package com.yxz.wulibibiji.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName sonComment
 */
@TableName(value = "sonComment")
public class Soncomment implements Serializable {
    /**
     * 主键，评论主键
     */
    @TableId(type = IdType.AUTO)
    private Integer sonCommentId;

    /**
     * 评论的父级评论id，默认为null
     */
    private Integer sonCommentParentId;

    /**
     * 外键，对应user_id
     */
    private String sonCommentUserId;

    /**
     * 外键，对应回复用户的id
     */
    private String sonCommentReplyUserId;

    /**
     * 评论内容
     */
    private String sonCommentContent;

    /**
     * 评论点赞数
     */
    private Integer sonCommentLikeCount;

    /**
     * 评论的创建时间
     */
    private Date sonCommentCreatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private boolean isLiked;
    @TableField(exist = false)
    private String commentUserName;
    @TableField(exist = false)
    private String commentUserAvatar;
    @TableField(exist = false)
    private String commentReplyName;
    @TableField(exist = false)
    private String commentReplyAvatar;

    public Soncomment() {
    }

    public Soncomment(Integer sonCommentParentId, String sonCommentUserId, String sonCommentReplyUserId, String sonCommentContent, Date sonCommentCreatedTime) {
        this.sonCommentParentId = sonCommentParentId;
        this.sonCommentUserId = sonCommentUserId;
        this.sonCommentReplyUserId = sonCommentReplyUserId;
        this.sonCommentContent = sonCommentContent;
        this.sonCommentCreatedTime = sonCommentCreatedTime;
    }

    public Soncomment(Integer sonCommentId, Integer sonCommentParentId, String sonCommentUserId, String sonCommentReplyUserId, String sonCommentContent, Integer sonCommentLikeCount, Date sonCommentCreatedTime, boolean isLiked, String commentUserName, String commentUserAvatar, String commentReplyName, String commentReplyAvatar) {
        this.sonCommentId = sonCommentId;
        this.sonCommentParentId = sonCommentParentId;
        this.sonCommentUserId = sonCommentUserId;
        this.sonCommentReplyUserId = sonCommentReplyUserId;
        this.sonCommentContent = sonCommentContent;
        this.sonCommentLikeCount = sonCommentLikeCount;
        this.sonCommentCreatedTime = sonCommentCreatedTime;
        this.isLiked = isLiked;
        this.commentUserName = commentUserName;
        this.commentUserAvatar = commentUserAvatar;
        this.commentReplyName = commentReplyName;
        this.commentReplyAvatar = commentReplyAvatar;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getCommentUserAvatar() {
        return commentUserAvatar;
    }

    public void setCommentUserAvatar(String commentUserAvatar) {
        this.commentUserAvatar = commentUserAvatar;
    }

    public String getCommentReplyName() {
        return commentReplyName;
    }

    public void setCommentReplyName(String commentReplyName) {
        this.commentReplyName = commentReplyName;
    }

    public String getCommentReplyAvatar() {
        return commentReplyAvatar;
    }

    public void setCommentReplyAvatar(String commentReplyAvatar) {
        this.commentReplyAvatar = commentReplyAvatar;
    }

    /**
     * 主键，评论主键
     */
    public Integer getSonCommentId() {
        return sonCommentId;
    }

    /**
     * 主键，评论主键
     */
    public void setSonCommentId(Integer sonCommentId) {
        this.sonCommentId = sonCommentId;
    }

    /**
     * 评论的父级评论id，默认为null
     */
    public Integer getSonCommentParentId() {
        return sonCommentParentId;
    }

    /**
     * 评论的父级评论id，默认为null
     */
    public void setSonCommentParentId(Integer sonCommentParentId) {
        this.sonCommentParentId = sonCommentParentId;
    }

    /**
     * 外键，对应user_id
     */
    public String getSonCommentUserId() {
        return sonCommentUserId;
    }

    /**
     * 外键，对应user_id
     */
    public void setSonCommentUserId(String sonCommentUserId) {
        this.sonCommentUserId = sonCommentUserId;
    }

    /**
     * 外键，对应回复用户的id
     */
    public String getSonCommentReplyUserId() {
        return sonCommentReplyUserId;
    }

    /**
     * 外键，对应回复用户的id
     */
    public void setSonCommentReplyUserId(String sonCommentReplyUserId) {
        this.sonCommentReplyUserId = sonCommentReplyUserId;
    }

    /**
     * 评论内容
     */
    public String getSonCommentContent() {
        return sonCommentContent;
    }

    /**
     * 评论内容
     */
    public void setSonCommentContent(String sonCommentContent) {
        this.sonCommentContent = sonCommentContent;
    }

    /**
     * 评论点赞数
     */
    public Integer getSonCommentLikeCount() {
        return sonCommentLikeCount;
    }

    /**
     * 评论点赞数
     */
    public void setSonCommentLikeCount(Integer sonCommentLikeCount) {
        this.sonCommentLikeCount = sonCommentLikeCount;
    }

    /**
     * 评论的创建时间
     */
    public Date getSonCommentCreatedTime() {
        return sonCommentCreatedTime;
    }

    /**
     * 评论的创建时间
     */
    public void setSonCommentCreatedTime(Date sonCommentCreatedTime) {
        this.sonCommentCreatedTime = sonCommentCreatedTime;
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
        Soncomment other = (Soncomment) that;
        return (this.getSonCommentId() == null ? other.getSonCommentId() == null : this.getSonCommentId().equals(other.getSonCommentId()))
                && (this.getSonCommentParentId() == null ? other.getSonCommentParentId() == null : this.getSonCommentParentId().equals(other.getSonCommentParentId()))
                && (this.getSonCommentUserId() == null ? other.getSonCommentUserId() == null : this.getSonCommentUserId().equals(other.getSonCommentUserId()))
                && (this.getSonCommentReplyUserId() == null ? other.getSonCommentReplyUserId() == null : this.getSonCommentReplyUserId().equals(other.getSonCommentReplyUserId()))
                && (this.getSonCommentContent() == null ? other.getSonCommentContent() == null : this.getSonCommentContent().equals(other.getSonCommentContent()))
                && (this.getSonCommentLikeCount() == null ? other.getSonCommentLikeCount() == null : this.getSonCommentLikeCount().equals(other.getSonCommentLikeCount()))
                && (this.getSonCommentCreatedTime() == null ? other.getSonCommentCreatedTime() == null : this.getSonCommentCreatedTime().equals(other.getSonCommentCreatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSonCommentId() == null) ? 0 : getSonCommentId().hashCode());
        result = prime * result + ((getSonCommentParentId() == null) ? 0 : getSonCommentParentId().hashCode());
        result = prime * result + ((getSonCommentUserId() == null) ? 0 : getSonCommentUserId().hashCode());
        result = prime * result + ((getSonCommentReplyUserId() == null) ? 0 : getSonCommentReplyUserId().hashCode());
        result = prime * result + ((getSonCommentContent() == null) ? 0 : getSonCommentContent().hashCode());
        result = prime * result + ((getSonCommentLikeCount() == null) ? 0 : getSonCommentLikeCount().hashCode());
        result = prime * result + ((getSonCommentCreatedTime() == null) ? 0 : getSonCommentCreatedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sonCommentId=").append(sonCommentId);
        sb.append(", sonCommentParentId=").append(sonCommentParentId);
        sb.append(", sonCommentUserId=").append(sonCommentUserId);
        sb.append(", sonCommentReplyUserId=").append(sonCommentReplyUserId);
        sb.append(", sonCommentContent=").append(sonCommentContent);
        sb.append(", sonCommentLikeCount=").append(sonCommentLikeCount);
        sb.append(", sonCommentCreatedTime=").append(sonCommentCreatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}