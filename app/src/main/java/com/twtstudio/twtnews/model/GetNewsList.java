package com.twtstudio.twtnews.model;

import java.util.List;

/**
 * Created by 冀辰阳 on 2016/4/19.
 */
public interface GetNewsList {
    /**
     * 生成第一页新闻的数据源
     * @param Type
     */
    List<NewsBean> getFirstPage(int Type);

    /**
     * 输入type和，page，获取更多的新闻，配合footer使用
     * @param Type
     * @param page
     */
    List<NewsBean> getMore(int Type,int page);
}
