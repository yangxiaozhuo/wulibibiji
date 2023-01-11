package com.yxz.wulibibiji.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName article
 */
@TableName(value = "article")
public class Article implements Serializable {
    /**
     * 文章id
     */
    @TableId(type = IdType.AUTO)
    private Integer articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章浏览量
     */
    private Integer articleViewCount;

    /**
     * 文章点赞量
     */
    private Integer articleLikeCount;

    /**
     * 文章评论数量
     */
    private Integer articleCommentCount;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 文章图片
     */
    private String articleImg;

    /**
     * 逻辑删除0表示未删除，1表示删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 外键，对应category_id
     */
    private Integer articleCategoryId;

    /**
     * 外键，对应user_id
     */
    private String articleUserId;

    /**
     * 分类表中对应category_name
     */
    private String articleCategoryName;

    @TableField(exist = false)
    private boolean isLiked;
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    /**
     * 文章id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 文章id
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 文章标题
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * 文章标题
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * 文章内容
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * 文章内容
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    /**
     * 文章浏览量
     */
    public Integer getArticleViewCount() {
        return articleViewCount;
    }

    /**
     * 文章浏览量
     */
    public void setArticleViewCount(Integer articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    /**
     * 文章点赞量
     */
    public Integer getArticleLikeCount() {
        return articleLikeCount;
    }

    /**
     * 文章点赞量
     */
    public void setArticleLikeCount(Integer articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }

    /**
     * 文章评论数量
     */
    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    /**
     * 文章评论数量
     */
    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    /**
     * 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 文章图片
     */
    public String getArticleImg() {
        return articleImg;
    }

    /**
     * 文章图片
     */
    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    /**
     * 逻辑删除0表示未删除，1表示删除
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 逻辑删除0表示未删除，1表示删除
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 外键，对应category_id
     */
    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    /**
     * 外键，对应category_id
     */
    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    /**
     * 外键，对应user_id
     */
    public String getArticleUserId() {
        return articleUserId;
    }

    /**
     * 外键，对应user_id
     */
    public void setArticleUserId(String articleUserId) {
        this.articleUserId = articleUserId;
    }

    /**
     * 分类表中对应category_name
     */
    public String getArticleCategoryName() {
        return articleCategoryName;
    }

    /**
     * 分类表中对应category_name
     */
    public void setArticleCategoryName(String articleCategoryName) {
        this.articleCategoryName = articleCategoryName;
    }

    public Article() {
    }

    public Article(Integer articleId, String articleTitle, String articleContent, Integer articleViewCount, Integer articleLikeCount, Integer articleCommentCount, Date createdTime, Date updateTime, String articleImg, Integer isDeleted, Integer articleCategoryId, String articleUserId, String articleCategoryName) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleViewCount = articleViewCount;
        this.articleLikeCount = articleLikeCount;
        this.articleCommentCount = articleCommentCount;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
        this.articleImg = articleImg;
        this.isDeleted = isDeleted;
        this.articleCategoryId = articleCategoryId;
        this.articleUserId = articleUserId;
        this.articleCategoryName = articleCategoryName;
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
        Article other = (Article) that;
        return (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
                && (this.getArticleTitle() == null ? other.getArticleTitle() == null : this.getArticleTitle().equals(other.getArticleTitle()))
                && (this.getArticleContent() == null ? other.getArticleContent() == null : this.getArticleContent().equals(other.getArticleContent()))
                && (this.getArticleViewCount() == null ? other.getArticleViewCount() == null : this.getArticleViewCount().equals(other.getArticleViewCount()))
                && (this.getArticleLikeCount() == null ? other.getArticleLikeCount() == null : this.getArticleLikeCount().equals(other.getArticleLikeCount()))
                && (this.getArticleCommentCount() == null ? other.getArticleCommentCount() == null : this.getArticleCommentCount().equals(other.getArticleCommentCount()))
                && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getArticleImg() == null ? other.getArticleImg() == null : this.getArticleImg().equals(other.getArticleImg()))
                && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
                && (this.getArticleCategoryId() == null ? other.getArticleCategoryId() == null : this.getArticleCategoryId().equals(other.getArticleCategoryId()))
                && (this.getArticleUserId() == null ? other.getArticleUserId() == null : this.getArticleUserId().equals(other.getArticleUserId()))
                && (this.getArticleCategoryName() == null ? other.getArticleCategoryName() == null : this.getArticleCategoryName().equals(other.getArticleCategoryName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getArticleTitle() == null) ? 0 : getArticleTitle().hashCode());
        result = prime * result + ((getArticleContent() == null) ? 0 : getArticleContent().hashCode());
        result = prime * result + ((getArticleViewCount() == null) ? 0 : getArticleViewCount().hashCode());
        result = prime * result + ((getArticleLikeCount() == null) ? 0 : getArticleLikeCount().hashCode());
        result = prime * result + ((getArticleCommentCount() == null) ? 0 : getArticleCommentCount().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getArticleImg() == null) ? 0 : getArticleImg().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getArticleCategoryId() == null) ? 0 : getArticleCategoryId().hashCode());
        result = prime * result + ((getArticleUserId() == null) ? 0 : getArticleUserId().hashCode());
        result = prime * result + ((getArticleCategoryName() == null) ? 0 : getArticleCategoryName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", articleContent=").append(articleContent);
        sb.append(", articleViewCount=").append(articleViewCount);
        sb.append(", articleLikeCount=").append(articleLikeCount);
        sb.append(", articleCommentCount=").append(articleCommentCount);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", articleImg=").append(articleImg);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", articleCategoryId=").append(articleCategoryId);
        sb.append(", articleUserId=").append(articleUserId);
        sb.append(", articleCategoryName=").append(articleCategoryName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}