package com.twtstudio.twtnews.presenter;

import com.twtstudio.twtnews.model.NewsBean;

import java.util.List;

/**
 * Created by 冀辰阳 on 2016/4/19.
 */
public interface NewsListPresenter
{
    /**
     * 刷新新闻界面
     */

    void refresh();

    /**
     * 加载下一页新闻
     */
    void loadNews(int Page);

    /**
     * 打开新闻详情列表
     */
    void showContent();

    /**
     * model获得数据后的回调接口
     * @param beanList
     */
    void postData(List<NewsBean> beanList);

    void postError();

}
