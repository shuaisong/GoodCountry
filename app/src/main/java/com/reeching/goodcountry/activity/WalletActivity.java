package com.reeching.goodcountry.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.bean.InOutBean;
import com.reeching.goodcountry.util.LogUtil;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.StatusBarUtil;
import com.reeching.goodcountry.view.CustomLoadMoreView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActivity extends AppCompatActivity {
    @BindView(R.id._balance)
    TextView mBalance;
    @BindView(R.id.scan)
    ImageView mScan;
    @BindView(R.id.in_out)
    RecyclerView mIn_out;
    private BaseQuickAdapter<InOutBean, BaseViewHolder> mAdapter;
    private ArrayList<InOutBean> mInOutBeans;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
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

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mIn_out.setLayoutManager(manager);
        mInOutBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<InOutBean, BaseViewHolder>(R.layout.in_out_item, mInOutBeans) {

            @Override
            protected void convert(BaseViewHolder helper, InOutBean item) {
                helper.setText(R.id.money, item.getMoney() + "");
                helper.setText(R.id.date, item.getDate());
                helper.setText(R.id.type, item.getType());
            }
        };
        mIn_out.addItemDecoration(new SpaceItemDecoration(1, R.color.divider));

        mIn_out.setAdapter(mAdapter);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtil.d("onLoadMoreRequested" + ":page:" + page);
                initData();
                mAdapter.loadMoreComplete();
            }
        }, mIn_out);
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            InOutBean inOutBean = new InOutBean();
            inOutBean.setDate("2019-1-1 1:1:1");
            inOutBean.setType(i % 2 == 0 ? "收入" : "支出");
            inOutBean.setMoney(i % 2 == 0 ? 1000 : -1000);
            mInOutBeans.add(inOutBean);
        }
        ++page;
        mAdapter.notifyDataSetChanged();
    }

}
