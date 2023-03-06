package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yxz.wulibibiji.dto.EsArticleDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Article;
import com.yxz.wulibibiji.service.EsArticleRepository;
import com.yxz.wulibibiji.service.EsArticleService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * @author yangxiaozhuo
 * @date 2023/03/05
 */
@Service
public class EsArticleServiceImpl implements EsArticleService {

    @Autowired
    private EsArticleRepository esRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

//    @Override
//    public Result search(String key) {
//        Pageable pageable = PageRequest.of(0,10);
////        Pageable pageable = PageRequest.of(page - 1, size, Direction.fromString(order),orderColumn);
//        return Result.ok(esRepository.findByArticleTitleOrArticleContent(key, key,pageable));
//    }

    @Override
    public Result search(String key) {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置高亮作用于articleTitle和articleContent 且设置片段大小为
        //fragmentSize默认是100，如果你不设置，并且你查询的这个关键字里面有一大段话
        //比如这段话开头和结尾包含（关键字）,那么在高亮返回的数组中只有开头和结尾，中间的省略了
        //为了防止这种情况，更具自己的字段长度去匹配此处的长度，不宜过大
        highlightBuilder.field("articleTitle"); //.fragmentSize(20);
        highlightBuilder.field("articleContent"); //.fragmentSize(20);
        //设置高亮前置标签
        highlightBuilder.preTags("<span style='color:red'>");
        //设置高亮后置标签
        highlightBuilder.postTags("</span>");

        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StrUtil.isNotBlank(key)) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("articleTitle", key));
            boolQueryBuilder.should(QueryBuilders.matchQuery("articleContent", key));
        }
        //分页
        query.withQuery(boolQueryBuilder).withPageable(PageRequest.of(0, 10)).withHighlightBuilder(highlightBuilder);
        SearchHits<EsArticleDTO> searchHits = elasticsearchRestTemplate.search(query.build(), EsArticleDTO.class);
        return Result.ok(searchHits.getSearchHits());
    }

    @Override
    public void addArticle(Article article) {
        esRepository.save(BeanUtil.toBean(article, EsArticleDTO.class));
    }

//    public Result addArticleAll(ArrayList<EsArticle> es) {
//        Iterable<EsArticle> execute = transactionTemplate.execute((status) -> esRepository.saveAll(es));
//        return Result.ok(execute);
//    }
//    @Override
//    public Result searchAuthorAndPrice(String auth, Integer price) {
//        return Result.ok(esRepository.findByAuthorAndPrice(auth, price));
//    }
//    @Override
//    public Result searchByArticleTitleOrAticleContent(String title, String content) {
//        return Result.ok(esRepository.findByArticleTitleOrArticleContent(title, content));
//    }
}
