package com.yxz.wulibibiji.service;

import com.yxz.wulibibiji.dto.EsArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


/**
 * @author yangxiaozhuo
 * @date 2023/03/05
 */
@Component
public interface EsArticleRepository extends ElasticsearchRepository<EsArticleDTO, String> {

    @Highlight(fields = {
            @HighlightField(name = "articleTitle"),
            @HighlightField(name = "articleContent")
    }, parameters = @HighlightParameters(preTags = {"<span style='color:red'>"}, postTags = {"</span>"}, numberOfFragments = 0)
    )
    Page<EsArticleDTO> findByArticleTitleOrArticleContent(String title, String content, Pageable pageable);

//    @Highlight(fields = {
//            @HighlightField(name = "articleTitle")
//    },parameters = @HighlightParameters(preTags = {"<span style='color:red'>"}, postTags = {"</span>"}, numberOfFragments = 0)
//    )
//    SearchHits<EsArticleDTO> findByArticleTitle(String keyword);

//    List<EsArticle> findByAuthorAndPrice(String name, Integer price);
}
