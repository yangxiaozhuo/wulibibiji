package com.yxz.wulibibiji.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * 外键，对应category_id
     */
    private Integer articleCategoryId;

    /**
     * 图片列表
     */
    List<MultipartFile> files;

    public ArticleDTO() {
    }

    public ArticleDTO(String articleTitle, String articleContent, Integer articleCategoryId,List<MultipartFile> files) {
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleCategoryId = articleCategoryId;
        this.files = files;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
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

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }
}
