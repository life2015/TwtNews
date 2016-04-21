package com.twtstudio.twtnews.presenter;

import android.os.Handler;
import android.os.Message;

import com.twtstudio.twtnews.model.GetNewsListImpl2;
import com.twtstudio.twtnews.model.GetNewsList;
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
    private List<NewsBean> newsBeanList=new ArrayList<>();
    private int TYPE;
    Handler handler=new MyHandler();
    FutureTask<List<NewsBean>> futureTask;
    public NewsListPresenterImpl(int TYPE) {
        this.getNewsList=new GetNewsListImpl2();
        this.newsFragmentView=new NewsFragmentViewImpl();
        this.TYPE=TYPE;
    }


    @Override
    public void refresh() {
        newsFragmentView.refresh(true);
        newsBeanList.clear();
        FutureTask<List<NewsBean>> futureTask=new FutureTask<>(new AsyncGetNews());
        new Thread(futureTask).start();
        //List<NewsBean> beanList= getNewsList.getFirstPage(TYPE);
        List<NewsBean> beanList=new ArrayList<>();
            try {
                beanList=futureTask.get();
                newsBeanList.addAll(beanList);
                RecyclerViewAdapter adapter=newsFragmentView.getAdapter();
                adapter.setNewses(newsBeanList);
                adapter.notifyDataSetChanged();
                newsFragmentView.refresh(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


    }

    @Override
    public void loadNews(int Page) {
        futureTask=new FutureTask<>(new AsyncGetMoreNews(Page));
        new Thread(futureTask).start();
        //List<NewsBean> beanList= getNewsList.getFirstPage(TYPE);

    }

    @Override
    public void showContent() {

    }
    class AsyncGetNews implements Callable<List<NewsBean>>
    {

        @Override
        public List<NewsBean> call() throws Exception {
            return getNewsList.getFirstPage(TYPE);
        }
    }
    class AsyncGetMoreNews implements Callable<List<NewsBean>>
    {
        int Page;

        public AsyncGetMoreNews(int page) {
            Page = page;
        }

        @Override
        public List<NewsBean> call() throws Exception {
            List<NewsBean> result=getNewsList.getMore(TYPE,Page);
            handler.sendEmptyMessage(0x123);
            System.out.println("消息已经发送");
            return result;
        }
    }
    class MyHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x123)
            {
                try {
                    List<NewsBean> beanList=new ArrayList<>();
                    beanList=futureTask.get();
                    newsBeanList.addAll(beanList);
                    RecyclerViewAdapter adapter=newsFragmentView.getAdapter();
                    adapter.setNewses(newsBeanList);
                    adapter.notifyDataSetChanged();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消息已经处理");
        }
    }

}
