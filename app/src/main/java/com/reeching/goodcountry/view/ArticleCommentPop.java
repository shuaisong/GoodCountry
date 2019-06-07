package com.reeching.goodcountry.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.reeching.goodcountry.R;
import com.reeching.goodcountry.activity.ReplyCommentActivity;
import com.reeching.goodcountry.base.BasePopupWindow;

/**
 * Created by lenovo on 2019/1/29.
 * auther:lenovo
 * Date：2019/1/29
 */
public class ArticleCommentPop extends BasePopupWindow implements View.OnClickListener {

    private TextView mReward;
    private PopupWindow mRewardPop;
    private View rewardLocation;

    public ArticleCommentPop(Activity context, boolean rewardVisible) {
        super(context);
        setRewardVisible(rewardVisible);
    }


    @Override
    protected int getLayout() {
        return R.layout.comment_pop;
    }

    protected void initWindow() {
        mInstance.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mInstance.setOutsideTouchable(true);
        mInstance.setTouchable(true);
        mInstance.setAnimationStyle(R.style.mypopwindow_anim_style1);
        mInstance.setFocusable(true);
        darkenBackground(1f);
    }

    @Override
    protected void initSize() {
        DisplayMetrics mMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        mInstance = new PopupWindow(mPopupView);
        mInstance.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mInstance.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void initView() {
        mReward = mPopupView.findViewById(R.id.reward);
        mPopupView.findViewById(R.id.reply).setOnClickListener(this);
        mPopupView.findViewById(R.id.report).setOnClickListener(this);
        mReward.setOnClickListener(this);
    }

    private void setRewardVisible(boolean rewardVisible) {
        mReward.setVisibility(rewardVisible ? View.VISIBLE : View.GONE);
    }

    public void setRewardClickLocation(View view) {
        rewardLocation = view;
        mRewardPop = new RewardPop(context).getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reply:
                Intent intent = new Intent(context, ReplyCommentActivity.class);
                intent.putExtra("type", 2);
                context.startActivity(intent);
                break;
            case R.id.report:
                break;
            case R.id.reward:
                mRewardPop.showAtLocation(rewardLocation, Gravity.BOTTOM,0,0);
                break;
        }
        mInstance.dismiss();
    }
}
