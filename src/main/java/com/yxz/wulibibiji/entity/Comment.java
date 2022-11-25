package com.yxz.wulibibiji.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName comment
 */
@TableName(value ="comment")
public class Comment implements Serializable {
    /**
     * 主键，评论主键
     */
    @TableId(type = IdType.AUTO)
    private Integer commentId;

    /**
     * 外键，对应article_id
     */
    private Integer commentArticleId;

    /**
     * 外键，对应user_id
     */
    private String commentUserId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论点赞数
     */
    private Integer commentLikeCount;

    /**
     * 评论的评论数
     */
    private Integer commentCount;

    /**
     * 评论的创建时间
     */
    private Date commentCreatedTime;

    /**
     * 评论的父级评论id，默认为null
     */
    private Integer commentParentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键，评论主键
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 主键，评论主键
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * 外键，对应article_id
     */
    public Integer getCommentArticleId() {
        return commentArticleId;
    }

    /**
     * 外键，对应article_id
     */
    public void setCommentArticleId(Integer commentArticleId) {
        this.commentArticleId = commentArticleId;
    }

    /**
     * 外键，对应user_id
     */
    public String getCommentUserId() {
        return commentUserId;
    }

    /**
     * 外键，对应user_id
     */
    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    /**
     * 评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 评论点赞数
     */
    public Integer getCommentLikeCount() {
        return commentLikeCount;
    }

    /**
     * 评论点赞数
     */
    public void setCommentLikeCount(Integer commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }

    /**
     * 评论的评论数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 评论的评论数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 评论的创建时间
     */
    public Date getCommentCreatedTime() {
        return commentCreatedTime;
    }

    /**
     * 评论的创建时间
     */
    public void setCommentCreatedTime(Date commentCreatedTime) {
        this.commentCreatedTime = commentCreatedTime;
    }

    /**
     * 评论的父级评论id，默认为null
     */
    public Integer getCommentParentId() {
        return commentParentId;
    }

    /**
     * 评论的父级评论id，默认为null
     */
    public void setCommentParentId(Integer commentParentId) {
        this.commentParentId = commentParentId;
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
        Comment other = (Comment) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getCommentArticleId() == null ? other.getCommentArticleId() == null : this.getCommentArticleId().equals(other.getCommentArticleId()))
            && (this.getCommentUserId() == null ? other.getCommentUserId() == null : this.getCommentUserId().equals(other.getCommentUserId()))
            && (this.getCommentContent() == null ? other.getCommentContent() == null : this.getCommentContent().equals(other.getCommentContent()))
            && (this.getCommentLikeCount() == null ? other.getCommentLikeCount() == null : this.getCommentLikeCount().equals(other.getCommentLikeCount()))
            && (this.getCommentCount() == null ? other.getCommentCount() == null : this.getCommentCount().equals(other.getCommentCount()))
            && (this.getCommentCreatedTime() == null ? other.getCommentCreatedTime() == null : this.getCommentCreatedTime().equals(other.getCommentCreatedTime()))
            && (this.getCommentParentId() == null ? other.getCommentParentId() == null : this.getCommentParentId().equals(other.getCommentParentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getCommentArticleId() == null) ? 0 : getCommentArticleId().hashCode());
        result = prime * result + ((getCommentUserId() == null) ? 0 : getCommentUserId().hashCode());
        result = prime * result + ((getCommentContent() == null) ? 0 : getCommentContent().hashCode());
        result = prime * result + ((getCommentLikeCount() == null) ? 0 : getCommentLikeCount().hashCode());
        result = prime * result + ((getCommentCount() == null) ? 0 : getCommentCount().hashCode());
        result = prime * result + ((getCommentCreatedTime() == null) ? 0 : getCommentCreatedTime().hashCode());
        result = prime * result + ((getCommentParentId() == null) ? 0 : getCommentParentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", commentArticleId=").append(commentArticleId);
        sb.append(", commentUserId=").append(commentUserId);
        sb.append(", commentContent=").append(commentContent);
        sb.append(", commentLikeCount=").append(commentLikeCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", commentCreatedTime=").append(commentCreatedTime);
        sb.append(", commentParentId=").append(commentParentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}