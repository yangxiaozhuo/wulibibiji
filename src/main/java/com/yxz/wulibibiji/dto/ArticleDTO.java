package com.yxz.wulibibiji.dto;

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
     * 文章图片
     */
    private String articleImg;

    /**
     * 外键，对应category_id
     */
    private Integer articleCategoryId;

    public ArticleDTO() {
    }

    public ArticleDTO(String articleTitle, String articleContent, String articleImg, Integer articleCategoryId) {
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleImg = articleImg;
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

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }
}
