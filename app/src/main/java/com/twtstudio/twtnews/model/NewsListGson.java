package com.twtstudio.twtnews.model;

import java.util.List;

/**
 * Created by 冀辰阳 on 2016/4/19.
 * 新闻列表用于配合gson框架
 */
public class NewsListGson {
    public  int error_code;
    public  String message;
    public  List<NewsBean> data;
}
