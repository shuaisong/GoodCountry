package com.reeching.goodcountry.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.reeching.goodcountry.R;
import com.reeching.goodcountry.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthenticationDoctorActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.num_tv)
    TextView mNumTv;
    @BindView(R.id.num)
    EditText mNum;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.verify_tv)
    TextView mVerifyTv;
    @BindView(R.id.verify)
    EditText mVerify;
    @BindView(R.id.view3)
    View mView3;
    @BindView(R.id.area_tv)
    TextView mAreaTv;
    @BindView(R.id.area)
    EditText mArea;
    @BindView(R.id.view4)
    View mView4;
    @BindView(R.id.hospital_tv)
    TextView mHospitalTv;
    @BindView(R.id.hospital)
    EditText mHospital;
    @BindView(R.id.view5)
    View mView5;
    @BindView(R.id.img_tv)
    TextView mImgTv;
    @BindView(R.id.submit)
    Button mSubmit;
    @BindView(R.id.get_verify)
    TextView mGetVerify;
    @BindView(R.id.img)
    ImageView mImg;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_doctor);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.submit, R.id.get_verify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                startActivity(new Intent(this, AuthenticationSuccessActivity.class));
                finish();
                break;
            case R.id.get_verify:
                mGetVerify.setEnabled(false);
                if (mCountDownTimer == null)
                    mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            mGetVerify.setText(String.format(getString(R.string.secondget), millisUntilFinished / 1000));
                        }

                        @Override
                        public void onFinish() {
                            mGetVerify.setText(getString(R.string.get_verify));
                            mGetVerify.setEnabled(true);
                        }
                    };
                mCountDownTimer.start();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }
}
