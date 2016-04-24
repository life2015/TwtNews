package com.twtstudio.twtnews.model;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.twtstudio.twtnews.NetUtils;
import com.twtstudio.twtnews.presenter.NewsListPresenter;
import com.twtstudio.twtnews.presenter.NewsListPresenterImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冀辰阳 on 2016/4/19.
 */
public class GetNewsListImpl implements GetNewsList {
    private static List<NewsBean> beanList=new ArrayList<>();
    //示例API：http://open.twtstudio.com/api/v1/news/2/page/1
    String API_BASE="http://open.twtstudio.com/api/v1/news/";
    String API_NEWS_PAGE="/page/";
    int TYPE=1;
    int PAGE=1;
    NewsListPresenter newsListPresenter;
    public GetNewsListImpl(NewsListPresenterImpl newsListPresenter) {
        this.newsListPresenter=newsListPresenter;
    }

    @Override
    public void getFirstPage(int Type) {
        if (beanList!=null)
        {
            beanList.clear();
        }

        new GetNewsTask().execute(Type,1);

    }

    @Override
    public void getMore(int Type, int page) {
        new GetNewsTask().execute(Type,page);


    }
    class GetNewsTask extends AsyncTask<Integer,Void,List<NewsBean>>
    {
        List<NewsBean> tempList=new ArrayList<>();
        URL url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected List<NewsBean> doInBackground(Integer... params) {
            String urlString=API_BASE+String.valueOf(params[0])+API_NEWS_PAGE+String.valueOf(params[1]);
            try {
                url=new URL(urlString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                tempList= NetUtils.getJsonData(NetUtils.readStream(url.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tempList;
        }

        @Override
        protected void onPostExecute(List<NewsBean> tempNewsBeenList) {
            super.onPostExecute(tempNewsBeenList);
            //beanList.addAll(beanList.size(),tempNewsBeenList);
//            System.out.println(beanList);
            newsListPresenter.postData(tempNewsBeenList);
            Log.d("jcy","data------>posted");
        }
    }
}
