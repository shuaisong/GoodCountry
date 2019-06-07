package com.reeching.goodcountry.view;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.reeching.goodcountry.R;
import com.reeching.goodcountry.base.BasePopupWindow;

/**
 * Created by lenovo on 2019/1/28.
 * auther:lenovo
 * Date：2019/1/28
 */
public class AuthenticationPop extends BasePopupWindow {
    public AuthenticationPop(Activity context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.authentication_pop;
    }

    @Override
    protected void initSize() {
        DisplayMetrics mMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        mInstance = new PopupWindow(mPopupView, (int) (mMetrics.widthPixels * 0.9f), mMetrics.heightPixels / 6);
    }

    protected void initView() {

    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        TextView doctor = mPopupView.findViewById(R.id.doctor);
        TextView patient = mPopupView.findViewById(R.id.patient);
        doctor.setOnClickListener(listener);
        patient.setOnClickListener(listener);
    }
}
