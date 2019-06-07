package com.reeching.goodcountry.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.base.BaseActivity;
import com.reeching.goodcountry.bean.ShopCarBean;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerActivityComponent;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.view.TitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SureOrderActivity extends BaseActivity {

    @BindView(R.id.title)
    TitleView mTitle;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.phone)
    TextView mPhone;
    @BindView(R.id.address)
    TextView mAddress;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.order_recycler)
    RecyclerView mOrderRecycler;
    @BindView(R.id.sum_price)
    TextView mSumPrice;
    @BindView(R.id.sum_coin)
    TextView mSumCoin;
    private List<ShopCarBean> mShopCarBeans;
    private BaseQuickAdapter<ShopCarBean, BaseViewHolder> mAdapter;
    private int mSum_coin;

    @Override
    protected void initData() {
        for (int i = 0; i < 3; i++) {
            ShopCarBean shopCarBean = new ShopCarBean();
            shopCarBean.setPath(Constant.hotel_imgs[i]);
            shopCarBean.setName("xxx酒店");
            shopCarBean.setCoin(1110);
            shopCarBean.setMoney(111);
            shopCarBean.setNum(i + 1);
            mShopCarBeans.add(shopCarBean);
        }
        getPrice();
        mSum_coin = 2222;
        mSumCoin.setText(String.format(getString(R.string.wallet_coin_sum), mSum_coin));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initViews() {
        mOrderRecycler.setNestedScrollingEnabled(false);
        mOrderRecycler.setHasFixedSize(true);
        mOrderRecycler.setLayoutManager(new LinearLayoutManager(this));
        mOrderRecycler.addItemDecoration(new SpaceItemDecoration(2, getResources().getColor(R.color.gray)));
        mShopCarBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<ShopCarBean, BaseViewHolder>(R.layout.car_item, mShopCarBeans) {
            @Override
            protected void convert(BaseViewHolder helper, ShopCarBean item) {
                helper.getView(R.id.line).setVisibility(View.GONE);
                helper.getView(R.id.delete).setVisibility(View.GONE);
                helper.setText(R.id.money, "￥" + item.getMoney() + "/天");
                helper.setText(R.id.coin, "或" + item.getCoin() + "币/天");
                helper.setText(R.id.num_tv, "数量：" + item.getNum());
                Glide.with(SureOrderActivity.this).asBitmap()
                        .error(R.mipmap.error).placeholder(R.mipmap.img_shoppingmall_default_jd)
                        .load(item.getPath()).into((ImageView) helper.getView(R.id.img));
            }
        };
        mOrderRecycler.setAdapter(mAdapter);
        mTitle.setBackClick(this);
    }

    private void getPrice() {
        int need_money = 0;
        int need_coin = 0;
        int good_sum = 0;
        for (ShopCarBean bean :
                mShopCarBeans) {
            need_coin += bean.getCoin() * bean.getNum();
            need_money += bean.getMoney() * bean.getNum();
            good_sum += bean.getNum();
        }
        String content = String.format(getString(R.string.car_sum), good_sum, need_money, need_coin);
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(Color.RED);
        builder.setSpan(foregroundColorSpan1, content.indexOf("：") + 1, content.indexOf("或"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(foregroundColorSpan, content.indexOf("或") + 1, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mSumPrice.setText(builder);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_sure_order;
    }

    @OnClick(R.id.sure_order)
    public void onViewClicked() {
        startActivity(new Intent(this, PayActivity.class));
    }

}
