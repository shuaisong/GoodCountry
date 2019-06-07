package com.reeching.goodcountry.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.reeching.goodcountry.R;
import com.reeching.goodcountry.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthenticationSuccessActivity extends AppCompatActivity {

    @BindView(R.id.article)
    Button mArticle;
    @BindView(R.id.home)
    Button mHome;
    @BindView(R.id.imageView)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_success);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.article, R.id.home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.article:
                finish();
                break;
            case R.id.home:
                finish();
                break;
        }
    }
}
