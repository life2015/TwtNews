package com.twtstudio.twtnews;

import com.google.gson.Gson;
import com.twtstudio.twtnews.model.NewsBean;
import com.twtstudio.twtnews.model.NewsListGson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by 冀辰阳 on 2016/4/19.
 */
public class NetUtils {
    //读取网络上的数据转化为字符串
    public static String readStream(InputStream is) {
        InputStreamReader isr;
        String result = " ";
        try {
            String line = " ";
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<NewsBean> getJsonData(String jsonString)
    {
        Gson gson=new Gson();
        NewsListGson newsListGson=gson.fromJson(jsonString,NewsListGson.class);
        if (newsListGson.error_code==-1)
        {return newsListGson.newsBeanList;}
        else return null;
    }
}
