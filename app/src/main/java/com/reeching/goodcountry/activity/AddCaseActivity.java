package com.reeching.goodcountry.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.reeching.goodcountry.R;
import com.reeching.goodcountry.util.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.submit, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.submit:
                setResult(Activity.RESULT_OK);
                finish();
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
