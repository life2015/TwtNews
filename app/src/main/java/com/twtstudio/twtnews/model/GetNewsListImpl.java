package com.twtstudio.twtnews.model;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.twtstudio.twtnews.NetUtils;

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
    boolean getting=false;
    @Override
    public List<NewsBean> getFirstPage(int Type) {
        if (beanList!=null)
        {
            beanList.clear();
        }

        new GetNewsTask().execute(Type,1);
        while (getting)
        {
            System.out.println("getting");
        }
        return beanList;
    }

    @Override
    public List<NewsBean> getMore(int Type, int page) {
        new GetNewsTask().execute(Type,page);
        return beanList;


    }
    class GetNewsTask extends AsyncTask<Integer,Void,List<NewsBean>>
    {
        List<NewsBean> tempList=new ArrayList<>();
        URL url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getting=true;
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
            getting=false;
            System.out.println(beanList);
        }
    }
}
