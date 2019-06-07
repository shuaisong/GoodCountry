package com.reeching.goodcountry.base;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.reeching.goodcountry.R;

/**
 * Created by lenovo on 2018/8/31.
 * auther:lenovo
 * Date：2018/8/31
 */
public abstract class BasePopupWindow {
    protected Activity context;
    protected PopupWindow mInstance;
    protected View mPopupView;
    public BasePopupWindow(){}
    public BasePopupWindow(Activity context) {
        this.context = context;
        mPopupView = LayoutInflater.from(context).inflate(getLayout(), null);
        initView();
        initSize();
        initEvent();
        initWindow();
    }

    protected abstract int getLayout();

    protected abstract void initSize();

    protected abstract void initView();

    public PopupWindow getInstance() {
        return mInstance;
    }

    protected void initWindow() {
        mInstance.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mInstance.setOutsideTouchable(true);
        mInstance.setTouchable(true);
        mInstance.setAnimationStyle(R.style.mypopwindow_anim_style);
        mInstance.setFocusable(true);
        darkenBackground(0.5f);
    }

    protected void darkenBackground(float f) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = f;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    protected void initEvent() {
        mInstance.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });
    }
}
