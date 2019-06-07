package com.reeching.goodcountry.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.reeching.goodcountry.BaseApplication;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.util.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/4/19.
 * auther:lenovo
 * Date：2018/4/19
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }


    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return super.shouldShowRequestPermissionRationale(permission);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        bind = ButterKnife.bind(this);
        setupActivityComponent(BaseApplication.getInstance().getAppComponent());
        initViews();
        initData();
    }


    protected abstract void initData();

    protected abstract void initViews();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    public abstract int getLayoutView();

}
