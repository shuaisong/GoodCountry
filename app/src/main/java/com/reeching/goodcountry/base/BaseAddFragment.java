package com.reeching.goodcountry.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reeching.goodcountry.BaseApplication;
import com.reeching.goodcountry.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/1/23.
 * auther:lenovo
 * Date：2019/1/23
 */
public abstract class BaseAddFragment extends Fragment {

    private Unbinder mBind;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayout(), container, false);
        mBind = ButterKnife.bind(this, rootView);
        setupFragComponent(BaseApplication.getInstance().getAppComponent());
        initView();
        initData();
        return rootView;
    }

    protected abstract void setupFragComponent(AppComponent appComponent);

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
