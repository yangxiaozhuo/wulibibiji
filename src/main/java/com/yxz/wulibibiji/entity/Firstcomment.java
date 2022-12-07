package com.yxz.wulibibiji.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName firstComment
 */
@TableName(value ="firstComment")
public class Firstcomment implements Serializable {
    /**
     * 主键，评论主键
     */
    @TableId(type = IdType.AUTO)
    private Integer firstCommentId;

    /**
     * 外键，对应article_id
     */
    private Integer firstCommentArticleId;

    /**
     * 外键，对应user_id
     */
    private String firstCommentUserId;

    /**
     * 评论内容
     */
    private String firstCommentContent;

    /**
     * 评论点赞数
     */
    private Integer firstCommentLikeCount;

    /**
     * 评论的评论数
     */
    private Integer firstCommentCount;

    /**
     * 评论的创建时间
     */
    private Date firstCommentCreatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键，评论主键
     */
    public Integer getFirstCommentId() {
        return firstCommentId;
    }

    /**
     * 主键，评论主键
     */
    public void setFirstCommentId(Integer firstCommentId) {
        this.firstCommentId = firstCommentId;
    }

    /**
     * 外键，对应article_id
     */
    public Integer getFirstCommentArticleId() {
        return firstCommentArticleId;
    }

    /**
     * 外键，对应article_id
     */
    public void setFirstCommentArticleId(Integer firstCommentArticleId) {
        this.firstCommentArticleId = firstCommentArticleId;
    }

    /**
     * 外键，对应user_id
     */
    public String getFirstCommentUserId() {
        return firstCommentUserId;
    }

    /**
     * 外键，对应user_id
     */
    public void setFirstCommentUserId(String firstCommentUserId) {
        this.firstCommentUserId = firstCommentUserId;
    }

    /**
     * 评论内容
     */
    public String getFirstCommentContent() {
        return firstCommentContent;
    }

    /**
     * 评论内容
     */
    public void setFirstCommentContent(String firstCommentContent) {
        this.firstCommentContent = firstCommentContent;
    }

    /**
     * 评论点赞数
     */
    public Integer getFirstCommentLikeCount() {
        return firstCommentLikeCount;
    }

    /**
     * 评论点赞数
     */
    public void setFirstCommentLikeCount(Integer firstCommentLikeCount) {
        this.firstCommentLikeCount = firstCommentLikeCount;
    }

    /**
     * 评论的评论数
     */
    public Integer getFirstCommentCount() {
        return firstCommentCount;
    }

    /**
     * 评论的评论数
     */
    public void setFirstCommentCount(Integer firstCommentCount) {
        this.firstCommentCount = firstCommentCount;
    }

    /**
     * 评论的创建时间
     */
    public Date getFirstCommentCreatedTime() {
        return firstCommentCreatedTime;
    }

    /**
     * 评论的创建时间
     */
    public void setFirstCommentCreatedTime(Date firstCommentCreatedTime) {
        this.firstCommentCreatedTime = firstCommentCreatedTime;
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
        Firstcomment other = (Firstcomment) that;
        return (this.getFirstCommentId() == null ? other.getFirstCommentId() == null : this.getFirstCommentId().equals(other.getFirstCommentId()))
            && (this.getFirstCommentArticleId() == null ? other.getFirstCommentArticleId() == null : this.getFirstCommentArticleId().equals(other.getFirstCommentArticleId()))
            && (this.getFirstCommentUserId() == null ? other.getFirstCommentUserId() == null : this.getFirstCommentUserId().equals(other.getFirstCommentUserId()))
            && (this.getFirstCommentContent() == null ? other.getFirstCommentContent() == null : this.getFirstCommentContent().equals(other.getFirstCommentContent()))
            && (this.getFirstCommentLikeCount() == null ? other.getFirstCommentLikeCount() == null : this.getFirstCommentLikeCount().equals(other.getFirstCommentLikeCount()))
            && (this.getFirstCommentCount() == null ? other.getFirstCommentCount() == null : this.getFirstCommentCount().equals(other.getFirstCommentCount()))
            && (this.getFirstCommentCreatedTime() == null ? other.getFirstCommentCreatedTime() == null : this.getFirstCommentCreatedTime().equals(other.getFirstCommentCreatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFirstCommentId() == null) ? 0 : getFirstCommentId().hashCode());
        result = prime * result + ((getFirstCommentArticleId() == null) ? 0 : getFirstCommentArticleId().hashCode());
        result = prime * result + ((getFirstCommentUserId() == null) ? 0 : getFirstCommentUserId().hashCode());
        result = prime * result + ((getFirstCommentContent() == null) ? 0 : getFirstCommentContent().hashCode());
        result = prime * result + ((getFirstCommentLikeCount() == null) ? 0 : getFirstCommentLikeCount().hashCode());
        result = prime * result + ((getFirstCommentCount() == null) ? 0 : getFirstCommentCount().hashCode());
        result = prime * result + ((getFirstCommentCreatedTime() == null) ? 0 : getFirstCommentCreatedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", firstCommentId=").append(firstCommentId);
        sb.append(", firstCommentArticleId=").append(firstCommentArticleId);
        sb.append(", firstCommentUserId=").append(firstCommentUserId);
        sb.append(", firstCommentContent=").append(firstCommentContent);
        sb.append(", firstCommentLikeCount=").append(firstCommentLikeCount);
        sb.append(", firstCommentCount=").append(firstCommentCount);
        sb.append(", firstCommentCreatedTime=").append(firstCommentCreatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}