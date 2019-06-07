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
import com.reeching.goodcountry.util.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/1/23.
 * auther:lenovo
 * Date：2019/1/23
 */
public abstract class BasePageFragment extends Fragment {

    private Unbinder mBind;
    protected View rootView;

    public boolean isFirstVisible() {
        return isFirstVisible;
    }

    private boolean isFirstVisible = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayout(), container, false);
        mBind = ButterKnife.bind(this, rootView);
        setupFragComponent(BaseApplication.getInstance().getAppComponent());
        initView();
        return rootView;
    }



    /**
     * viewpager展示时
     *
     * @param isVisibleToUser true if this fragment's UI is currently visible to the user (default),
     *                        false if it is not.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    protected void onFragmentFirstVisible() {
        LogUtil.d(getTag());
        initData();
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
