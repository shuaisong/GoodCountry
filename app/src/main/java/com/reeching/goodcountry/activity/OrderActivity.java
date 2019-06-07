package com.reeching.goodcountry.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.adapter.VPAdapter;
import com.reeching.goodcountry.fragment.OrderFragment;
import com.reeching.goodcountry.util.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.order_vp)
    ViewPager mOrderVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
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
        ArrayList<Fragment> fragments = new ArrayList<>();
        OrderFragment allOrderFragment = OrderFragment.newInstance(0);
        OrderFragment orderFragment1 = OrderFragment.newInstance(1);
        OrderFragment orderFragment2 = OrderFragment.newInstance(2);
        OrderFragment orderFragment3 = OrderFragment.newInstance(3);
        fragments.add(allOrderFragment);
        fragments.add(orderFragment1);
        fragments.add(orderFragment2);
        fragments.add(orderFragment3);
        mOrderVp.setOffscreenPageLimit(4);
        mOrderVp.setAdapter(new VPAdapter(getSupportFragmentManager(), fragments, Constant.Order_type));
        mOrderVp.setCurrentItem(0);
        mTab.setupWithViewPager(mOrderVp);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
