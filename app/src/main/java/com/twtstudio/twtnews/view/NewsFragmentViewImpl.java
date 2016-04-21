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

    private static RecyclerView mRecyclerView;
    private static SwipeRefreshLayout mSwipeRefreshLayout;
    private  NewsListPresenter mNewsListPresenter;
    private static RecyclerViewAdapter mRecyclerViewAdapter;
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
        if (mSwipeRefreshLayout==null){Log.d("jcy","view null");}else {Log.d("jcy","view ok");}
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN);
        mNewsListPresenter=new NewsListPresenterImpl(getArguments().getInt("index"));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                Bundle bundle=getArguments();
                Log.d("jcy","post");
                mNewsListPresenter.refresh();
                mRecyclerView.setAdapter(mRecyclerViewAdapter);
                PAGE=2;
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Bundle bundle=getArguments();
                Log.d("jcy","post");
                mNewsListPresenter.refresh();
                mRecyclerView.setAdapter(mRecyclerViewAdapter);
                PAGE=2;
            }
        });
        final LinearLayoutManager layoutManger=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManger);
        mRecyclerView.setHasFixedSize(true);
        //实例化adapter，后面传给presenter
        mRecyclerViewAdapter=new RecyclerViewAdapter(null,getActivity());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalcount=layoutManger.getItemCount();
                int lastcount=layoutManger.findLastVisibleItemPosition();
                if (!loading&&lastcount+2>=totalcount)
                {
                    Bundle bundle=getArguments();
                    loading=true;
                    mNewsListPresenter.loadNews(PAGE);
                    loading=false;
                    PAGE++;
                    System.out.println("已获取");
                    //mRecyclerView.setAdapter(mRecyclerViewAdapter);
                    Log.d("jcy",".....");
                }
            }
        });
        return view;
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
}
