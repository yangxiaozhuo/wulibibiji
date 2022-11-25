package com.yxz.wulibibiji.dto;

import lombok.*;

/**
 * @author yangxiaozhuo
 * @date 2022/11/20
 */
public class ArticleDTO {

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章图片数量
     */
    private Integer articleImgCount;

    /**
     * 外键，对应category_id
     */
    private Integer articleCategoryId;

    public ArticleDTO() {
    }

    public ArticleDTO(String articleTitle, String articleContent, Integer articleImgCount, Integer articleCategoryId) {
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleImgCount = articleImgCount;
        this.articleCategoryId = articleCategoryId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getArticleImgCount() {
        return articleImgCount;
    }

    public void setArticleImgCount(Integer articleImgCount) {
        this.articleImgCount = articleImgCount;
    }

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }
}
