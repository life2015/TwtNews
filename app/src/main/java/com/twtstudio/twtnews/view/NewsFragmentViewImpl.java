package com.twtstudio.twtnews.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.twtstudio.twtnews.R;
import com.twtstudio.twtnews.presenter.NewsListPresenter;
import com.twtstudio.twtnews.presenter.NewsListPresenterImpl;
import com.twtstudio.twtnews.presenter.RecyclerViewAdapter;

/**
 * Created by 冀辰阳 on 2016/4/19.
 */
public class NewsFragmentViewImpl extends android.support.v4.app.Fragment implements NewsFragmentView {

    private  RecyclerView mRecyclerView;
    private  SwipeRefreshLayout mSwipeRefreshLayout;
    private  NewsListPresenter mNewsListPresenter;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private boolean loading=false;
    private int PAGE=2;//要获取的下一页
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static NewsFragmentViewImpl newInstance(int index) {

        Bundle args = new Bundle();
        args.putInt("index",index);
        NewsFragmentViewImpl fragment = new NewsFragmentViewImpl();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_main,container,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN);
        mNewsListPresenter=new NewsListPresenterImpl(getArguments().getInt("index"));
        mRecyclerViewAdapter=new RecyclerViewAdapter(null,getActivity());
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                Log.d("jcy","post");
                mNewsListPresenter.refresh();
                PAGE=2;
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("jcy","post");
                mNewsListPresenter.refresh();
                PAGE=2;
            }
        });
        final LinearLayoutManager layoutManger=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManger);
        mRecyclerView.setHasFixedSize(true);
        //实例化adapter，后面传给presenter

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalcount=layoutManger.getItemCount();
                int lastcount=layoutManger.findLastVisibleItemPosition();
                System.out.println("Loading="+loading+"   lastcount"+lastcount+"  totalcount"+totalcount);
                if (lastcount+2>=totalcount)
                {
                    Bundle bundle=getArguments();
//                    loading=true;
                    mNewsListPresenter.loadNews(PAGE);
                    PAGE++;
                    System.out.println("已获取------->");
                    //mRecyclerView.setAdapter(mRecyclerViewAdapter);
                    //Log.d("jcy",".....");
                }
            }
        });
        return view;
    }

    public boolean isLoading() {
        return loading;
    }

    @Override
    public void refresh(boolean flag ) {
        mSwipeRefreshLayout.setRefreshing(flag);
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        return mRecyclerViewAdapter;
    }

    @Override
    public void setLoading(boolean loading) {
        this.loading=loading;
        System.out.println("loading状态改变................" + this.loading);
    }

    @Override
    public void notifySet(RecyclerViewAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

}
