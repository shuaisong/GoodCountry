package com.reeching.goodcountry.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.bean.CaseBean;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaseManageActivity extends AppCompatActivity {

    @BindView(R.id.case_recycler)
    RecyclerView mCaseRecycler;
    private ArrayList<CaseBean> mCaseBeans;
    private BaseQuickAdapter<CaseBean, BaseViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_manage);
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
        for (int i = 0; i < 8; i++) {
            CaseBean caseBean = new CaseBean();
            caseBean.setPath("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548319382466&di=ebd7e21b73c2e5bf1f36133ace81f048&imgtype=0&src=http%3A%2F%2Fs7.sinaimg.cn%2Fmiddle%2F4b916806x7525f6e44bc6%26690");
            caseBean.setDisease("肿瘤");
            caseBean.setAge(23 + i);
            caseBean.setName("里斯");
            caseBean.setSex(i % 2 == 1 ? "男" : "女");
            mCaseBeans.add(caseBean);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mCaseBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<CaseBean, BaseViewHolder>(R.layout.case_item, mCaseBeans) {
            @Override
            protected void convert(BaseViewHolder helper, CaseBean item) {
                Glide.with(CaseManageActivity.this).asBitmap()
                        .placeholder(R.mipmap.img_my_default_head).error(R.mipmap.img_my_default_head)
                        .load(item.getPath()).into((ImageView) helper.getView(R.id.head));
                helper.setText(R.id.name, item.getName());
                helper.setText(R.id.disease, item.getDisease());
                helper.setText(R.id.age, String.valueOf(item.getAge()));
                helper.setText(R.id.sex, item.getSex());
            }
        };
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(CaseManageActivity.this, CaseInfoActivity.class));
            }
        });
        mCaseRecycler.addItemDecoration(new SpaceItemDecoration(1));
        mCaseRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCaseRecycler.setAdapter(mAdapter);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
