package com.reeching.goodcountry.view;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.reeching.goodcountry.R;
import com.reeching.goodcountry.base.BasePopupWindow;
import com.xw.repo.BubbleSeekBar;

/**
 * Created by lenovo on 2019/1/29.
 * auther:lenovo
 * Date：2019/1/29
 */
public class RewardPop extends BasePopupWindow implements View.OnClickListener {

    private TextView mReward;
    private BubbleSeekBar mSeekBar;

    RewardPop(Activity context) {
        super(context);
    }


    @Override
    protected int getLayout() {
        return R.layout.reward_pop;
    }

    @Override
    protected void initSize() {
        DisplayMetrics mMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        mInstance = new PopupWindow(mPopupView);
        mInstance.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mInstance.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void initView() {
        mReward = mPopupView.findViewById(R.id.reward_value);
        mReward.setText(String.format(context.getString(R.string.reward_coin), 0));
        mSeekBar = mPopupView.findViewById(R.id.reward_seek);
        mSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                mReward.setText(String.format(context.getString(R.string.reward_coin), progress));
            }
        });
        mPopupView.findViewById(R.id.reward_sure).setOnClickListener(this);
        mPopupView.findViewById(R.id.reward_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reward_sure:
                mSeekBar.setProgress(0);
                break;
            case R.id.reward_cancel:
                mSeekBar.setProgress(0);
                break;
        }
        mInstance.dismiss();
    }
}
