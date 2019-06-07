package com.reeching.goodcountry.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.bean.HomeCategoryBean;

import java.util.List;

/**
 * Created by lenovo on 2019/1/23.
 * auther:lenovo
 * Date：2019/1/23
 */
public class HomeCategoryAdapter extends BaseQuickAdapter<HomeCategoryBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public HomeCategoryAdapter(int layoutResId, @Nullable List<HomeCategoryBean> data) {
        super(layoutResId, data);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, HomeCategoryBean item) {
        helper.setImageResource(R.id.img, item.getPath());
        helper.setText(R.id.name, item.getTitle());
    }
}
