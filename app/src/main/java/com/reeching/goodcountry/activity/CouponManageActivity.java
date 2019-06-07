package com.reeching.goodcountry.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.bean.CouponBean;
import com.reeching.goodcountry.util.JsonCallback;
import com.reeching.goodcountry.util.PreferenceManager;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.StatusBarUtil;
import com.reeching.goodcountry.view.TitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponManageActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleView mTitle;
    @BindView(R.id.no_data)
    TextView mNoData;
    @BindView(R.id.coupon_recycler)
    RecyclerView mCouponRecycler;
    private List<CouponBean.CouponListBean> mCouponBeans;
    private BaseQuickAdapter<CouponBean.CouponListBean, BaseViewHolder> mAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_manage);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("加载中，请稍候。。。");
        mProgressDialog.show();
        getCouponBeans();
    }

    private void initView() {
        mTitle.setBackClick(this);
        mCouponRecycler.setLayoutManager(new LinearLayoutManager(this));
        mCouponRecycler.addItemDecoration(new SpaceItemDecoration(10, getResources().getColor(R.color.divider)));
        mCouponBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<CouponBean.CouponListBean, BaseViewHolder>(R.layout.coupon_item, mCouponBeans) {

            @Override
            protected void convert(BaseViewHolder helper, CouponBean.CouponListBean item) {
                switch (item.getCouponStatus()) {
                    case "2":
                        helper.getView(R.id.is_used).setVisibility(View.VISIBLE);
                        ((ImageView) helper.getView(R.id.is_used)).setImageResource(R.mipmap.img_coupon_use);
                        helper.getView(R.id.item).setBackgroundColor(getResources().getColor(R.color.gray));
                        break;
                    case "1":
                        helper.getView(R.id.is_used).setVisibility(View.VISIBLE);
                        ((ImageView) helper.getView(R.id.is_used)).setImageResource(R.mipmap.img_coupon_use);
                        helper.getView(R.id.item).setBackgroundColor(getResources().getColor(R.color.gray));
                        break;
                    case "0":
                        helper.getView(R.id.is_used).setVisibility(View.GONE);
                        helper.getView(R.id.item).setBackgroundColor(getResources().getColor(android.R.color.white));
                        break;
                }
                Glide.with(CouponManageActivity.this).asBitmap()
                        .placeholder(R.mipmap.img_shoppingmall_default_jd).error(R.mipmap.error)
                        .load(Constant.IMG_URL + item.getPhoto()).into((ImageView) helper.getView(R.id.img));
                helper.setText(R.id.price, "$" + item.getMoney())
                        .setText(R.id.type, Constant.COUPON_TYPE[Integer.valueOf(item.getType())])
                        .setText(R.id.description, item.getRemarks())
                        .setText(R.id.condition, "满" + item.getFullDec() + "可用")
                        .setText(R.id.time, "截至时间至" + item.getStrEndDate());
            }

        };
        mCouponRecycler.setAdapter(mAdapter);
    }

    public void getCouponBeans() {
        OkGo.<CouponBean>get(Constant.BASE_URL + Constant.COUPON).cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .tag(this).params("openId", PreferenceManager.getInstance().getString("openId"))
                .execute(new JsonCallback<CouponBean>(CouponBean.class) {
                    @Override
                    public void onSuccess(Response<CouponBean> response) {
                        if (mProgressDialog.isShowing()) mProgressDialog.dismiss();
                        if (response.body().getCouponList().size() <= 0) {
                            mNoData.setVisibility(View.VISIBLE);
                            return;
                        }
                        mCouponBeans.addAll(response.body().getCouponList());
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
