package com.twtstudio.twtnews.presenter;

import com.twtstudio.twtnews.model.GetNewsList;
import com.twtstudio.twtnews.model.GetNewsListImpl;
import com.twtstudio.twtnews.model.NewsBean;
import com.twtstudio.twtnews.view.NewsFragmentView;
import com.twtstudio.twtnews.view.NewsFragmentViewImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冀辰阳 on 2016/4/19.
 */
public class NewsListPresenterImpl implements NewsListPresenter{

    private GetNewsList getNewsList;
    private NewsFragmentView newsFragmentView;
    private List<NewsBean> newsBeanList=new ArrayList<>();
    private int TYPE;
    public NewsListPresenterImpl(int TYPE) {
        this.getNewsList=new GetNewsListImpl();
        newsFragmentView=new NewsFragmentViewImpl();
        this.TYPE=TYPE;
    }


    @Override
    public void refresh(int TYP,List<NewsBean> newsBeanList) {
        List<NewsBean> beanList= getNewsList.getFirstPage(TYPE);
        newsBeanList.addAll(beanList);
        newsFragmentView.refresh(beanList);
    }

    @Override
    public void loadNews() {

    }

    @Override
    public void showContent() {

    }



}
