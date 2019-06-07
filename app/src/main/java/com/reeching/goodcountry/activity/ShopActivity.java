package com.reeching.goodcountry.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.adapter.ShopAdapter;
import com.reeching.goodcountry.bean.ShopCategoryBean;
import com.reeching.goodcountry.util.LogUtil;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.StatusBarUtil;
import com.reeching.goodcountry.view.ClearEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopActivity extends AppCompatActivity {

    @BindView(R.id.search)
    ClearEditText mSearch;
    @BindView(R.id.first)
    RecyclerView mFirst;
    @BindView(R.id.second)
    RecyclerView mSecond;
    private List<String> mFirst_list;
    private List<ShopCategoryBean> mSecond_list;
    private ShopAdapter mShopAdapter;
    private BaseQuickAdapter<String, BaseViewHolder> mFirst_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mFirst_list.addAll(Arrays.asList(Constant.Shop_titles_Category));
        mFirst_adapter.notifyDataSetChanged();
        updateSecond(0);
    }

    private void updateSecond(int position) {
        mSecond_list.clear();
        String s = mFirst_list.get(position);
        ShopCategoryBean shopCategoryBean;
        shopCategoryBean = new ShopCategoryBean();
        shopCategoryBean.setLevel(ShopAdapter.TYPE_LEVEL_1);
        shopCategoryBean.setName(s);
        mSecond_list.add(shopCategoryBean);
        ShopCategoryBean ShopCategoryBean1;
        for (int k = 0; k < 7; k++) {
            ShopCategoryBean1 = new ShopCategoryBean();
            ShopCategoryBean1.setLevel(ShopAdapter.TYPE_LEVEL_2);
            ShopCategoryBean1.setName("xxx" + s);
            ShopCategoryBean1.setPath(Constant.hotel_imgs[k % 2]);
            ShopCategoryBean1.setPrice(111 + k);
            ShopCategoryBean1.setPrice1(1111 + k);
            mSecond_list.add(ShopCategoryBean1);
        }
        mShopAdapter.notifyDataSetChanged();
    }

    private void initView() {
        final Drawable mDrawable_left = getResources().getDrawable(R.mipmap.button_default_search);
        mDrawable_left.setBounds(0, 0, mDrawable_left.getMinimumWidth(), mDrawable_left.getMinimumHeight());
        mSearch.setCompoundDrawables(mDrawable_left, null, null, null);
        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    LogUtil.d("开始搜索");
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    assert inputManager != null;
                    inputManager.hideSoftInputFromWindow(mSearch.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    return true;
                }
                return false;
            }
        });
        mFirst_list = new ArrayList<>();
        mFirst_adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.community_first, mFirst_list) {

            @Override
            protected void convert(final BaseViewHolder helper, final String item) {
                helper.setNestView(R.id.name);
                helper.setText(R.id.name, item);
                if (mFirst_list.indexOf(item) == 0) {
                    helper.setTextColor(R.id.name, Color.GREEN);
                }
            }
        };
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mFirst.setLayoutManager(manager);
        mFirst.setAdapter(mFirst_adapter);
        mFirst_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            private int prePosition = 0;

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (prePosition != position) {
                    updateSecond(position);
                    TextView viewByPosition = (TextView) adapter.getViewByPosition(mFirst, position, R.id.name);
                    assert viewByPosition != null;
                    viewByPosition.setTextColor(Color.GREEN);
                    if (prePosition != -1) {
                        TextView viewPrePosition = (TextView) adapter.getViewByPosition(mFirst, prePosition, R.id.name);
                        assert viewPrePosition != null;
                        viewPrePosition.setTextColor(Color.GRAY);
                    }
                    prePosition = position;
                }
            }
        });
        mSecond_list = new ArrayList<>();
        mShopAdapter = new ShopAdapter(this, mSecond_list);
        mShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (((ShopCategoryBean) adapter.getData().get(position)).getLevel() == ShopAdapter.TYPE_LEVEL_2) {
                    startActivity(new Intent(ShopActivity.this, HotelDetailActivity.class));
                }
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mSecond.setLayoutManager(gridLayoutManager);
        mSecond.addItemDecoration(new SpaceItemDecoration(15));
        mSecond.setAdapter(mShopAdapter);
    }
}
