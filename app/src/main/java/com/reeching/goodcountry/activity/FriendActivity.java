package com.reeching.goodcountry.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.bean.FriendBean;
import com.reeching.goodcountry.util.JsonCallback;
import com.reeching.goodcountry.util.PreferenceManager;
import com.reeching.goodcountry.util.StatusBarUtil;
import com.reeching.goodcountry.util.ToastUtil;
import com.reeching.goodcountry.view.TitleView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleView mTitle;
    @BindView(R.id.level1)
    TextView mLevel1;
    @BindView(R.id.level2)
    TextView mLevel2;
    @BindView(R.id.level3)
    TextView mLevel3;
    @BindView(R.id.total)
    TextView mTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        ButterKnife.bind(this);
        mTitle.setBackClick(this);
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.loading));
        dialog.show();
        getData(dialog);

    }

    private void getData(final ProgressDialog dialog) {
        OkGo.<FriendBean>get(Constant.BASE_URL + Constant.FRIENDCOUNT)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST).params("openId", PreferenceManager.getInstance().getString("openId"))
                .execute(new JsonCallback<FriendBean>(FriendBean.class) {
                    @Override
                    public void onSuccess(Response<FriendBean> response) {
                        if (dialog.isShowing()) dialog.dismiss();
                        if (response.isSuccessful()) {
                            FriendBean bean = response.body();
                            mLevel1.setText(String.format(getResources().getString(R.string.coin), bean.getOne() * 10));
                            mLevel2.setText(String.format(getResources().getString(R.string.coin), bean.getTwo() * 10));
                            mLevel3.setText(String.format(getResources().getString(R.string.coin), bean.getThree() * 10));
                            mTotal.setText(String.format(getString(R.string.total_in), (bean.getThree() + bean.getTwo() + bean.getOne()) * 10));
                        } else
                            ToastUtil.showToast(FriendActivity.this, getString(R.string.error_net));
                    }

                    @Override
                    public void onError(Response<FriendBean> response) {
                        super.onError(response);
                    }
                });
    }
}
