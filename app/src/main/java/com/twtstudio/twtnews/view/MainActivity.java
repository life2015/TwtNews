package com.twtstudio.twtnews.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.twtstudio.twtnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冀辰阳 on 2016/4/3.
 */
public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main_test);
        toolbar= (Toolbar) findViewById(R.id.toolbar_test);

        setSupportActionBar(toolbar);

        viewPager= (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(NewsFragmentViewImpl.newInstance(1),"天大要闻");
        viewPagerAdapter.addFragment(NewsFragmentViewImpl.newInstance(2),"校园公告");
        viewPagerAdapter.addFragment(NewsFragmentViewImpl.newInstance(3),"社团风采");
        //这个API出现了问题
        //viewPagerAdapter.addFragment(new MyNewsFragment().newInstance(4),"院系动态");
        viewPagerAdapter.addFragment(NewsFragmentViewImpl.newInstance(5),"视点观察");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

    }

class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    public ViewPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
}
