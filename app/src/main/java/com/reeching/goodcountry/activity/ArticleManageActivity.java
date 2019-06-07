package com.reeching.goodcountry.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.reeching.goodcountry.R;
import com.reeching.goodcountry.adapter.VPAdapter;
import com.reeching.goodcountry.fragment.ArticleCollectionFragment;
import com.reeching.goodcountry.fragment.ArticleFragment;
import com.reeching.goodcountry.util.StatusBarUtil;
import com.reeching.goodcountry.view.TitleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleManageActivity extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.title)
    TitleView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articl_manage);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        boolean share = intent.getBooleanExtra("share", false);
        String openId = intent.getStringExtra("openId");
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArticleFragment articleFragment = new ArticleFragment();
        ArticleCollectionFragment articleCollectionFragment = new ArticleCollectionFragment();

        Bundle bundle = new Bundle();
        if (share) {
            bundle.putBoolean("share", true);
        }
        if (!TextUtils.isEmpty(openId)) {
            bundle.putString("openId", openId);
        }
        articleFragment.setArguments(bundle);
        articleCollectionFragment.setArguments(bundle);
        fragments.add(articleFragment);
        fragments.add(articleCollectionFragment);
        String[] strings = {"我的文章", "我的收藏"};
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), fragments, strings);
        mViewpager.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mViewpager);
        mTitle.setBackClick(this);
    }
}
