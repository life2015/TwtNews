package com.twtstudio.twtnews.view;

import com.twtstudio.twtnews.presenter.RecyclerViewAdapter;

/**
 * Created by 冀辰阳 on 2016/4/19.
 */
public interface NewsFragmentView {

    /**
     * 刷新view的界面
     */
    void refresh(boolean flag);

    /**
     * 加载下一页
     */
    void loadMore();
    /**
     * 错误提示
     */
    void showError();

    /**
     * 获取fragment的adapter
     */
    RecyclerViewAdapter getAdapter();
    void setLoading(boolean loading);

    /**
     * 通知UI线程设置adapter
     * @param adapter
     */
    void notifySet(RecyclerViewAdapter adapter);
}
