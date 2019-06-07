package com.reeching.goodcountry.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaseInfoActivity extends AppCompatActivity {

    @BindView(R.id.add_case)
    TextView mAddCase;
    @BindView(R.id.case_recycler)
    RecyclerView mCaseRecycler;
    @BindView(R.id.head)
    ImageView mHead;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_info);
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
        Glide.with(this).asBitmap().placeholder(R.mipmap.img_my_default_head).
                error(R.mipmap.img_my_default_head).load(Constant.head_path).circleCrop().into(mHead);

    }

    private void initView() {
        mList = new ArrayList<>();
        mCaseRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.treat_case_item, mList) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.title, item);
                helper.setNestView(R.id.title);
            }
        };
        mCaseRecycler.addItemDecoration(new SpaceItemDecoration(1));
        mCaseRecycler.setAdapter(mAdapter);
    }

    @OnClick(R.id.add_case)
    public void onViewClicked() {
        Intent intent = new Intent(this, AddCaseActivity.class);
        startActivityForResult(intent, Constant.ADDCASE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.ADDCASE && resultCode == Activity.RESULT_OK) {
            mList.add("第" + mList.size() + "次治疗病例");
            mAdapter.notifyDataSetChanged();
        }
    }
}
