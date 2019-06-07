package com.reeching.goodcountry.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.reeching.goodcountry.util.ToastUtil;
import com.reeching.goodcountry.view.TitleView;
import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopCarActivity extends BaseActivity {

    @BindView(R.id.title)
    TitleView mTitle;
    @BindView(R.id.good_recycler)
    RecyclerView mGoodRecycler;
    @BindView(R.id.sum_price)
    TextView mSumPrice;
    @BindView(R.id.percent1)
    TextView mPercent1;
    @BindView(R.id.percent2)
    TextView mPercent2;
    @BindView(R.id.seekBar)
    BubbleSeekBar mSeekBar;
    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.coin)
    TextView mCoin;
    @BindView(R.id.sum_coin)
    TextView mSumCoin;
    @BindView(R.id.car_scroll)
    NestedScrollView mScrollView;
    @BindView(R.id.settlement)
    Button mSettlement;
    private List<ShopCarBean> mShopCarBeans;
    private BaseQuickAdapter<ShopCarBean, BaseViewHolder> mAdapter;
    private int mSum_coin;
    private AlertDialog mDialog;
    private EditText mNum;
    private ImageView mPlus_modify;
    private ImageView mMinus_modify;
    private int mType;

    @Override
    protected void initData() {
        mType = getIntent().getIntExtra("type", Constant.CAR);
        int k = 5;
        switch (mType) {
            case Constant.CAR:
                break;
            case Constant.ADD_CAR:
                k = 1;
                mSettlement.setText(getResources().getString(R.string.sure));
                break;
            case Constant.BUY_NOW:
                k = 1;
                break;
        }
        for (int i = 0; i < k; i++) {
            ShopCarBean shopCarBean = new ShopCarBean();
            shopCarBean.setPath(Constant.hotel_imgs[i]);
            shopCarBean.setName("xxx酒店");
            shopCarBean.setCoin(1110);
            shopCarBean.setMoney(111);
            shopCarBean.setNum(i + 1);
            mShopCarBeans.add(shopCarBean);
        }
        getPrice();
        mAdapter.notifyDataSetChanged();
        mSum_coin = 22220;
        mCoin.setText(String.valueOf(mSum_coin));
        mSumCoin.setText(String.format(getString(R.string.wallet_coin_sum), mSum_coin));
        mSeekBar.getConfigBuilder().max(mSum_coin).min(0);
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
    protected void initViews() {

        mGoodRecycler.setHasFixedSize(true);
        mGoodRecycler.setNestedScrollingEnabled(false);
        mGoodRecycler.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mGoodRecycler.addItemDecoration(new SpaceItemDecoration(2, getResources().getColor(R.color.gray)));
        mShopCarBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<ShopCarBean, BaseViewHolder>(R.layout.car_item, mShopCarBeans) {
            @Override
            protected void convert(BaseViewHolder helper, ShopCarBean item) {
                helper.getView(R.id.num_tv).setVisibility(View.GONE);
                helper.setText(R.id.money, "￥" + item.getMoney() + "/天");
                helper.setText(R.id.coin, "或" + item.getCoin() + "币/天");
                helper.setText(R.id.num, String.valueOf(item.getNum()));
                if (item.getNum() == 1) {
                    helper.setImageResource(R.id.minus, R.mipmap.minus_circle_disable);
                }
                Glide.with(ShopCarActivity.this).asBitmap().error(R.mipmap.error).placeholder(R.mipmap.img_shoppingmall_default_jd).load(item.getPath()).into((ImageView) helper.getView(R.id.img));
                helper.setNestView(R.id.delete).setNestView(R.id.minus).setNestView(R.id.plus).setNestView(R.id.num);
            }
        };
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ImageView minus = (ImageView) adapter.getViewByPosition(mGoodRecycler, position, R.id.minus);
                int num = mShopCarBeans.get(position).getNum();
                switch (view.getId()) {
                    case R.id.minus:
                        if (num != 1) {
                            mShopCarBeans.get(position).setNum(--num);
                        }
                        if (mShopCarBeans.get(position).getNum() == 1) {
                            minus.setImageResource(R.mipmap.minus_circle_disable);
                        }
                        break;
                    case R.id.plus:
                        mShopCarBeans.get(position).setNum(++num);
                        assert minus != null;
                        minus.setImageResource(R.mipmap.minus_circle_fill);
                        break;
                    case R.id.delete:
                        mShopCarBeans.remove(position);
                        break;
                    case R.id.num:
                        if (mDialog == null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ShopCarActivity.this);
                            View inflate = LayoutInflater.from(ShopCarActivity.this).inflate(R.layout.modify_num, null);
                            mNum = inflate.findViewById(R.id.num);
                            DialogClickLister dialogClickLister = new DialogClickLister(position);
                            inflate.findViewById(R.id.cancel).setOnClickListener(dialogClickLister);
                            inflate.findViewById(R.id.sure).setOnClickListener(dialogClickLister);
                            mNum.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!"".equals(s.toString()) && Integer.valueOf(s.toString()) < 1) {
                                        mNum.setText(String.valueOf(1));
                                        mNum.setSelection(mNum.getText().length());
                                        ToastUtil.showToast(ShopCarActivity.this, "请输入大于0的数字");
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                }
                            });
                            mMinus_modify = inflate.findViewById(R.id.minus);
                            mPlus_modify = inflate.findViewById(R.id.plus);
                            mPlus_modify.setOnClickListener(dialogClickLister);
                            mMinus_modify.setOnClickListener(dialogClickLister);
                            builder.setView(inflate);
                            DisplayMetrics mMetrics = new DisplayMetrics();
                            getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
                            mDialog = builder.create();
                            WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
                            params.height = mMetrics.heightPixels / 3;
                        }
                        if (mShopCarBeans.get(position).getNum() == 1)
                            mMinus_modify.setImageResource(R.mipmap.minus_circle_disable);
                        else mMinus_modify.setImageResource(R.mipmap.minus_circle_fill);
                        mNum.setText(String.valueOf(mShopCarBeans.get(position).getNum()));
                        mNum.setSelection(mNum.getText().length());
                        mDialog.show();
                        break;
                }
                adapter.notifyDataSetChanged();
                getPrice();
            }
        });
        mGoodRecycler.setAdapter(mAdapter);
        mSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {

            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                mPercent1.setText(String.format(Locale.CHINESE, "%d%%", progress));
                mPercent2.setText(String.format(Locale.CHINESE, "%d%%", 100 - progress));
                mMoney.setText(String.valueOf(mSum_coin * progress / 1000));
                mCoin.setText(String.valueOf(mSum_coin - (mSum_coin * progress / 100)));
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
            }

        });
        mTitle.setBackClick(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_shop_car;
    }


    @OnClick({R.id.settlement})
    public void onViewClicked() {
        if (mType == Constant.ADD_CAR) {
            finish();
        } else
            startActivity(new Intent(this, SureOrderActivity.class));
    }

    class DialogClickLister implements View.OnClickListener {
        private int position;

        DialogClickLister(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Integer num;
            if ("".equals(mNum.getText().toString())) {
                num = mShopCarBeans.get(position).getNum();
            } else
                num = Integer.valueOf(mNum.getText().toString());
            switch (v.getId()) {
                case R.id.minus:
                    if (num != 1) {
                        mNum.setText(String.valueOf(--num));
                    }
                    if (num == 1) {
                        mMinus_modify.setImageResource(R.mipmap.minus_circle_disable);
                    }
                    break;
                case R.id.plus:
                    mNum.setText(String.valueOf(++num));
                    mMinus_modify.setImageResource(R.mipmap.minus_circle_fill);
                    break;
                case R.id.cancel:
                    mDialog.dismiss();
                    break;
                case R.id.sure:
                    mDialog.dismiss();
                    mShopCarBeans.get(position).setNum(num);
                    mAdapter.notifyDataSetChanged();
                    getPrice();
                    break;

            }
        }
    }

}
