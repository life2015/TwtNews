package com.twtstudio.twtnews.presenter;

import android.os.Handler;
import android.os.Message;

import com.twtstudio.twtnews.model.GetNewsList;
import com.twtstudio.twtnews.model.GetNewsListImpl;
import com.twtstudio.twtnews.model.NewsBean;
import com.twtstudio.twtnews.view.NewsFragmentView;
import com.twtstudio.twtnews.view.NewsFragmentViewImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by 冀辰阳 on 2016/4/19.
 */
public class NewsListPresenterImpl implements NewsListPresenter{

    private GetNewsList getNewsList;
    private NewsFragmentView newsFragmentView;
    private  List<NewsBean> newsBeanList=new ArrayList<>();
    private int TYPE;
    public NewsListPresenterImpl(int TYPE,NewsFragmentViewImpl fragmentView) {
        this.getNewsList=new GetNewsListImpl(this);
        this.newsFragmentView=fragmentView;
        this.TYPE=TYPE;
    }


    @Override
    public void refresh() {
        newsFragmentView.refresh(true);
        newsBeanList.clear();
        getNewsList.getFirstPage(TYPE);
        //List<NewsBean> beanList= getNewsList.getFirstPage(TYPE);

    }

    @Override
    public void loadNews(int Page) {
        if(!newsFragmentView.isLoading()) {
            newsFragmentView.setLoading(true);
            getNewsList.getMore(TYPE,Page);
            //List<NewsBean> beanList= getNewsList.getFirstPage(TYPE);
        }

    }

    @Override
    public void showContent() {

    }

    @Override
    public void postData(List<NewsBean> beanList) {
        newsBeanList.addAll(beanList);
        RecyclerViewAdapter adapter=newsFragmentView.getAdapter();
        newsFragmentView.setLoading(false);
        newsFragmentView.refresh(false);

        if (newsBeanList.size()<=20)
        {
            adapter.setNewses(newsBeanList);
            newsFragmentView.notifySetAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void postError() {
        newsFragmentView.showError();
    }


}
