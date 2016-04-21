package com.twtstudio.twtnews.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.twtstudio.twtnews.NetUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 冀辰阳 on 2016/4/20.
 */
public class GetNewsImpl2 implements GetNewsList {

    String API_BASE = "http://open.twtstudio.com/api/v1/news/";
    String API_NEWS_PAGE = "/page/";
    //private MyHandler myHandler=new MyHandler();
    private List<NewsBean> beanList = new ArrayList<>();

    @Override
    public List<NewsBean> getFirstPage(int Type) {

        Thread test = new Thread(new GetNews(Type, 1));
        test.start();
        try {
            test.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return beanList;

    }

    @Override
    public List<NewsBean> getMore(int Type, int page) {
        return null;
    }

    class GetNews implements Runnable {
        URL url;
        int TYPE = 1;
        int PAGE = 1;
        List<NewsBean> tempList = new ArrayList<>();

        public GetNews(int TYPE, int PAGE) {
            this.TYPE = TYPE;
            this.PAGE = PAGE;
        }

        @Override
        public void run() {
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
                System.out.println(tempList);
                beanList.addAll(tempList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*Message message=Message.obtain();
            message.what=0x123;
            message.obj=tempList;
            myHandler.sendMessage(message);*/
        }
    }
    /*class MyHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x123)
            {
                beanList.addAll((List<NewsBean>) msg.obj);
            }
        }
    }*/
}
