package com.reeching.goodcountry.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.util.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthenticationPatientActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.disease_tv)
    TextView mDiseaseTv;
    @BindView(R.id.disease)
    TextView mDisease;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.flag)
    TextView mFlag;
    @BindView(R.id.recycler_flag)
    RecyclerView mRecyclerFlag;
    @BindView(R.id.submit)
    Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_paient);
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
        ArrayList<String> flags = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            flags.add("标签" + i);
        }
        BaseQuickAdapter<String, BaseViewHolder> adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.community_third, flags) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.third_name, item);
            }
        };
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerFlag.setLayoutManager(gridLayoutManager);
        mRecyclerFlag.setAdapter(adapter);
    }

    @OnClick({R.id.back,R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                startActivity(new Intent(this, AuthenticationSuccessActivity.class));
                finish();
                break;
        }
    }
}
