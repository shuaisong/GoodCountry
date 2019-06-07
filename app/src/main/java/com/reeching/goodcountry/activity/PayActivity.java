package com.reeching.goodcountry.activity;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.reeching.goodcountry.R;
import com.reeching.goodcountry.base.BaseActivity;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerActivityComponent;
import com.reeching.goodcountry.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {

    @BindView(R.id.need_pay)
    TextView mNeedPay;
    @BindView(R.id.money_pay)
    TextView mMoneyPay;
    @BindView(R.id.radio_pay)
    RadioGroup mRadioPay;
    @BindView(R.id.coin_pay)
    TextView mCoinPay;
    private int money_pay;
    private int coin_pay;
    private AlertDialog mDialog;
    private View mLoading;
    private View mDone;


    @OnClick(R.id.sure_pay)
    public void onViewClicked() {
        if (money_pay > 0 && mRadioPay.getCheckedRadioButtonId() == -1) {
            ToastUtil.showToast(this, "请选择人民币支付方式");
        } else {
            if (mDialog == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.pay_dialog, null);
                mLoading = view.findViewById(R.id.line_loading);
                mDone = view.findViewById(R.id.line_done);
                View sure = view.findViewById(R.id.sure);
                builder.setView(view);
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                mDialog = builder.create();
            }
            mDialog.setCanceledOnTouchOutside(false);
            mLoading.setVisibility(View.VISIBLE);
            mDone.setVisibility(View.GONE);
            mDialog.show();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mDialog.setCanceledOnTouchOutside(true);
                            mLoading.setVisibility(View.GONE);
                            mDone.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }, 4 * 1000);
        }
    }

    @Override
    protected void initData() {
        money_pay = 670;
        coin_pay = 300;
        mNeedPay.setText(String.format(getString(R.string.need_pay), money_pay, coin_pay));
        mMoneyPay.setText(String.format(getString(R.string.money), money_pay));
        mCoinPay.setText(String.format(getString(R.string.coin), coin_pay));
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_pay;
    }
}
