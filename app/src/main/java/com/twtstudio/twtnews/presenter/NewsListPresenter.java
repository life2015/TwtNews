package com.twtstudio.twtnews.presenter;

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
    void loadNews();

    /**
     * 打开新闻详情列表
     */
    void showContent();


}
