package com.twtstudio.twtnews.view;

/**
 * Created by 冀辰阳 on 2016/4/19.
 */
public interface NewsFragmentView {

    /**
     * 刷新view的界面
     */
    void refresh();

    /**
     * 加载下一页
     */
    void loadMore();
    /**
     * 错误提示
     */
    void showError();
}
