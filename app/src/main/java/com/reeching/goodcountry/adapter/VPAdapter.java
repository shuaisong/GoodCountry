package com.reeching.goodcountry.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class VPAdapter extends FragmentPagerAdapter {
    private List<Fragment> mViewList;
    private String[] titles;

    public VPAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mViewList = list;
    }

    public VPAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm);
        mViewList = list;
        this.titles = titles;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position item position
     */
    @Override
    public Fragment getItem(int position) {
        return mViewList.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((Fragment) obj).getView();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}

