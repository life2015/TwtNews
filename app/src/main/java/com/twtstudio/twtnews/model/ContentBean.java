package com.twtstudio.twtnews.model;

import android.graphics.Bitmap;

/**
 * Created by 冀辰阳 on 2016/3/23.
 */
public class ContentBean {
    public String subject;
    public String content;
    //新闻来源，供稿，审要，摄影
    public String newscome;
    public String gonggao;
    public String shenggao;
    public String sheying;
    public Bitmap bitmap;
    public int visitcount;

    public ContentBean() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewscome() {
        return newscome;
    }

    public void setNewscome(String newscome) {
        this.newscome = newscome;
    }

    public String getGonggao() {
        return gonggao;
    }

    public void setGonggao(String gonggao) {
        this.gonggao = gonggao;
    }

    public String getShenggao() {
        return shenggao;
    }

    public void setShenggao(String shenggao) {
        this.shenggao = shenggao;
    }

    public String getSheying() {
        return sheying;
    }

    public void setSheying(String sheying) {
        this.sheying = sheying;
    }

    public int getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(int visitcount) {
        this.visitcount = visitcount;
    }
}
