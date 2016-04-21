package com.twtstudio.twtnews.model;

import android.util.Log;

import com.twtstudio.twtnews.NetUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by 冀辰阳 on 2016/4/20.
 */
public class GetNewsListImpl2 implements GetNewsList {

    String API_BASE = "http://open.twtstudio.com/api/v1/news/";
    String API_NEWS_PAGE = "/page/";
    //private MyHandler myHandler=new MyHandler();


    @Override
    public List<NewsBean> getFirstPage(int Type) {
        return getNews(Type,1);

    }

    @Override
    public List<NewsBean> getMore(int Type, int page)
    {
        return getNews(Type,page);
    }




        public List<NewsBean> getNews(int TYPE,int PAGE) {
            URL url=null;
            List<NewsBean> tempList = new ArrayList<>();
            String urlString = API_BASE + String.valueOf(TYPE) + API_NEWS_PAGE + String.valueOf(PAGE);
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("开始获取");
                tempList = NetUtils.getJsonData(NetUtils.readStream(url.openStream()));
                Log.d("jcy", "获取");
//                System.out.println(tempList);
//                beanList.addAll(tempList);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return tempList;
        }


}
