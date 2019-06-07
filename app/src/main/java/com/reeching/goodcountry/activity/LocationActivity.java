package com.reeching.goodcountry.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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
import com.reeching.goodcountry.base.BaseActivity;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerActivityComponent;
import com.reeching.goodcountry.util.LocationUtil;
import com.reeching.goodcountry.util.LogUtil;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.view.ClearEditText;
import com.reeching.goodcountry.view.TitleView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

public class LocationActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.search)
    ClearEditText mSearch;
    @BindView(R.id.current_location)
    TextView mCurrentLocation;
    @BindView(R.id.current)
    TextView mCurrent;
    @BindView(R.id.history_location)
    TextView mHistoryLocation;
    @BindView(R.id.history_recycler)
    RecyclerView mHistoryRecycler;
    @BindView(R.id.city)
    TextView mCity;
    @BindView(R.id.city_recycler)
    RecyclerView mCityRecycler;
    @BindView(R.id.title)
    TitleView mTitle;
    private BaseQuickAdapter<String, BaseViewHolder> mHistoryAdapter;
    private BaseQuickAdapter<String, BaseViewHolder> mHotCityAdapter;
    private ArrayList<String> mHotCities;
    private ArrayList<String> mHistoryCities;

    @Override
    protected void initData() {
        LocationUtil.getLocation(mCurrentLocation);
        mHotCities.addAll(Arrays.asList(Constant.CITIES));
        mHistoryCities.addAll(Arrays.asList(Constant.CITIES));
        mHotCityAdapter.notifyDataSetChanged();
        mHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PackageManager.PERMISSION_GRANTED == grantResults[0]) {
            LocationUtil.getLocation(mCurrentLocation);
        }
    }

    @Override
    protected void initViews() {
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

        mCityRecycler.setHasFixedSize(true);
        mCityRecycler.setNestedScrollingEnabled(false);
        mHistoryRecycler.setHasFixedSize(true);
        mHistoryRecycler.setNestedScrollingEnabled(false);
        mHistoryRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        mCityRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        mHistoryRecycler.addItemDecoration(new SpaceItemDecoration(10));
        mCityRecycler.addItemDecoration(new SpaceItemDecoration(10));
        mHotCities = new ArrayList<>();
        mHistoryCities = new ArrayList<>();
        mHistoryAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.location_item, mHistoryCities) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.text, item);
            }
        };
        mHistoryAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                mHistoryCities.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        mHistoryAdapter.setOnItemClickListener(this);

        mHistoryRecycler.setAdapter(mHistoryAdapter);
        mHotCityAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.location_item, mHotCities) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.text, item);
            }
        };
        mHotCityAdapter.setOnItemClickListener(this);
        mCityRecycler.setAdapter(mHotCityAdapter);
        mTitle.setBackClick(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_location;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        if (adapter == mHistoryAdapter) {
            intent.putExtra("city", mHistoryCities.get(position));
        }
        if (adapter == mHotCityAdapter) {
            intent.putExtra("city", mHotCities.get(position));
        }
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
