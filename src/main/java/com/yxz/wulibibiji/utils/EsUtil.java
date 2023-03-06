package com.yxz.wulibibiji.utils;

import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

/**
 * @author yangxiaozhuo
 * @date 2023/03/06
 */
public class EsUtil {

    public static HighlightBuilder HIGHLIGHTBUILDER = new HighlightBuilder();

    static {
        //设置高亮作用于articleTitle和articleContent 且设置片段大小为
        //fragmentSize默认是100，如果你不设置，并且你查询的这个关键字里面有一大段话
        //比如这段话开头和结尾包含（关键字）,那么在高亮返回的数组中只有开头和结尾，中间的省略了
        //为了防止这种情况，更具自己的字段长度去匹配此处的长度，不宜过大
        HIGHLIGHTBUILDER.field("articleTitle"); //.fragmentSize(20);
        HIGHLIGHTBUILDER.field("articleContent"); //.fragmentSize(20);
        //设置高亮前置标签
        HIGHLIGHTBUILDER.preTags("<span style='color:red'>");
        //设置高亮后置标签
        HIGHLIGHTBUILDER.postTags("</span>");

    }
}
