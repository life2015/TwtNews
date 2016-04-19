package com.twtstudio.twtnews.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twtstudio.twtnews.R;
import com.twtstudio.twtnews.presenter.NewsListPresenter;
import com.twtstudio.twtnews.presenter.NewsListPresenterImpl;
import com.twtstudio.twtnews.presenter.RecyclerViewAdapter;

/**
 * Created by 冀辰阳 on 2016/4/19.
 */
public class NewsFragmentViewImpl extends android.support.v4.app.Fragment implements NewsFragmentView {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsListPresenter mNewsListPresenter;
    RecyclerViewAdapter mRecyclerViewAdapter;
    private boolean loading=false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsListPresenter=new NewsListPresenterImpl(getArguments().getInt("index"));
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_main,container,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
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
                    mNewsListPresenter.refresh(bundle.getInt("index"));
                }
            }
        });

    }

    @Override
    public void refresh(RecyclerView ) {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void showError() {

    }
}
