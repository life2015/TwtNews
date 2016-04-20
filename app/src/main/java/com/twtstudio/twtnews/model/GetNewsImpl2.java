package com.twtstudio.twtnews.model;

import java.util.List;

/**
 * Created by 冀辰阳 on 2016/4/20.
 */
public class GetNewsImpl2 implements GetNewsList {
    @Override
    public List<NewsBean> getFirstPage(int Type) {
        return null;
    }

    @Override
    public List<NewsBean> getMore(int Type, int page) {
        return null;
    }
    class GetNews implements Runnable
    {

        @Override
        public void run() {

        }
    }
}
