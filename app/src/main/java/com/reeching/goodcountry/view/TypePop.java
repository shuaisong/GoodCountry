package com.reeching.goodcountry.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.base.BasePopupWindow;
import com.reeching.goodcountry.bean.TwoCategoryBean;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.TextCallBack;

import java.util.ArrayList;

/**
 * Created by lenovo on 2019/1/30.
 * auther:lenovo
 * Date：2019/1/30
 */
public class TypePop extends BasePopupWindow {
    public TypePop(Activity context, TwoCategoryBean bean) {
        super();
        mTwoCategoryBean = bean;
        this.context = context;
        mPopupView = LayoutInflater.from(context).inflate(getLayout(), null);
        initView();
        initSize();
        initEvent();
        initWindow();
    }

    private TwoCategoryBean mTwoCategoryBean;

    @Override
    protected void initWindow() {
        mInstance.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mInstance.setOutsideTouchable(true);
        mInstance.setTouchable(true);
        mInstance.setAnimationStyle(R.style.mypopwindow_anim_style1);
        mInstance.setFocusable(true);
        darkenBackground(0.5f);
    }

    @Override
    protected int getLayout() {
        return R.layout.type_pop;
    }

    @Override
    protected void initSize() {
        DisplayMetrics mMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        mInstance = new PopupWindow(mPopupView, mMetrics.widthPixels, mMetrics.heightPixels / 2);
    }

    @Override
    protected void initView() {
        final RecyclerView first = mPopupView.findViewById(R.id.first);
        final RecyclerView second = mPopupView.findViewById(R.id.second);
        first.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        second.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        final ArrayList<TwoCategoryBean.ObjectMultiArrayBean> level1 = new ArrayList<>();

        final ArrayList<TwoCategoryBean.ObjectMultiArrayBean> level2 = new ArrayList<>();
        for (TwoCategoryBean.ObjectMultiArrayBean bean :
                mTwoCategoryBean.getObjectMultiArray()) {
            if ("二级".equals(bean.getTypeLevel())) {
                level1.add(bean);
            }
        }
        BaseQuickAdapter<TwoCategoryBean.ObjectMultiArrayBean, BaseViewHolder> adapter =
                new BaseQuickAdapter<TwoCategoryBean.ObjectMultiArrayBean, BaseViewHolder>(android.R.layout.simple_list_item_1, level1) {
                    @Override
                    protected void convert(BaseViewHolder helper, TwoCategoryBean.ObjectMultiArrayBean item) {
                        helper.setText(android.R.id.text1, item.getName());
                    }
                };
        final BaseQuickAdapter<TwoCategoryBean.ObjectMultiArrayBean, BaseViewHolder> adapter1 =
                new BaseQuickAdapter<TwoCategoryBean.ObjectMultiArrayBean, BaseViewHolder>(android.R.layout.simple_list_item_1, level2) {
                    @Override
                    protected void convert(BaseViewHolder helper, TwoCategoryBean.ObjectMultiArrayBean item) {
                        helper.setText(android.R.id.text1, item.getName());
                    }
                };
        first.addItemDecoration(new SpaceItemDecoration(2, context.getResources().getColor(R.color.gray)));
        second.addItemDecoration(new SpaceItemDecoration(2, context.getResources().getColor(R.color.gray)));
        first.setAdapter(adapter);
        second.setAdapter(adapter1);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            private int index = -1;

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView viewByPosition = (TextView) adapter.getViewByPosition(first, position, android.R.id.text1);
                TextView index_tv = (TextView) adapter.getViewByPosition(first, index, android.R.id.text1);
                viewByPosition.setTextColor(context.getResources().getColor(R.color.blue));
                viewByPosition.setBackgroundColor(Color.WHITE);
                if (index != -1 && index != position) {
                    index_tv.setTextColor(Color.BLACK);
                    index_tv.setBackgroundColor(context.getResources().getColor(R.color.divider));
                }
                index = position;
                level2.clear();
                for (TwoCategoryBean.ObjectMultiArrayBean bean :
                        mTwoCategoryBean.getObjectMultiArray()) {
                    if (bean.getParentId().equals(level1.get(position).getId())) {
                        level2.add(bean);
                    }
                }
                adapter1.notifyDataSetChanged();
            }
        });
        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTextCallBack.setType(level2.get(position));
                mInstance.dismiss();
            }
        });
    }

    private TextCallBack mTextCallBack;

    public void setTextCallBack(TextCallBack textCallBack) {
        mTextCallBack = textCallBack;
    }
}
