package com.reeching.goodcountry.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.previewlibrary.GPreviewActivity;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.ThumbViewInfo;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.base.BaseActivity;
import com.reeching.goodcountry.bean.ShopBean;
import com.reeching.goodcountry.bean.ShopCommentBean;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerActivityComponent;
import com.reeching.goodcountry.util.Imgloader;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.ToastUtil;
import com.reeching.goodcountry.view.TitleView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class ProxyDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title)
    TitleView mTitle;
    @BindView(R.id.good_banner)
    Banner mGoodBanner;
    @BindView(R.id.good_name)
    TextView mGoodName;
    @BindView(R.id.good_price)
    TextView mGoodPrice;
    @BindView(R.id.good_price1)
    TextView mGoodPrice1;
    @BindView(R.id.sold)
    TextView mSold;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.description)
    TextView mDescription;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.add_comment)
    ImageView mAddComment;
    @BindView(R.id.view3)
    View mView3;
    @BindView(R.id.shop_recycler)
    RecyclerView mShopRecycler;
    @BindView(R.id.bottom)
    LinearLayout mBottom;
    @BindView(R.id.shop)
    TextView mShop;
    @BindView(R.id.shop_car)
    TextView mShopCar;
    @BindView(R.id.add_car)
    TextView mAddCar;
    @BindView(R.id.buy_now)
    TextView mBuyNow;
    @BindView(R.id.room_type)
    TextView mRoomType;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.room_num)
    TextView mRoomNum;
    private int room_num = 1;
    private int room_temp = room_num;
    private BaseQuickAdapter<ShopCommentBean, BaseViewHolder> mAdapter;
    private List<ShopCommentBean> mCommentBeans;
    private OptionsPickerView<String> mRoomPicker;
    private EditText mNum;
    private AlertDialog mDialog;
    private ImageView mMinus_modify;
    private TimePickerView mTimePickerView1;
    private String mTime1 = null;
    private String mTime2 = null;
    private TimePickerView mTimePickerView2;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        ShopBean.JsonShopBean good = (ShopBean.JsonShopBean) intent.getSerializableExtra("good");
        SpannableStringBuilder span = new SpannableStringBuilder(good.getShopIntroduce());
        if (good.getShopIntroduce().length() >= 2)
            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mDescription.setText(span);
        List<String> imgs = new ArrayList<>();
        if (good.getPhotoArray() == null) {
            imgs.add(good.getPhoto());
        } else {
            imgs = good.getPhotoArray();
        }
        setBanner(mGoodBanner, null, imgs);
        mGoodPrice.setText(String.format("$%s/天", good.getHotelPrice()));
    }
    @Override
    protected void initViews() {
        mShopRecycler.setHasFixedSize(true);
        mShopRecycler.setNestedScrollingEnabled(false);
        mShopRecycler.setLayoutManager(new LinearLayoutManager(this));
        mCommentBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<ShopCommentBean, BaseViewHolder>(R.layout.good_comment_item, mCommentBeans) {

            @Override
            protected void convert(BaseViewHolder helper, final ShopCommentBean item) {
                ImageView mHead = helper.getView(R.id.head);
                RecyclerView img_recycler = helper.getView(R.id.img_recycler);
                helper.setText(R.id.name, item.getName());
                helper.setText(R.id.time, item.getDate());
                helper.setText(R.id.content, item.getContent());
                RequestOptions options = new RequestOptions().circleCrop();
                Glide.with(ProxyDetailActivity.this).asBitmap().error(R.mipmap.error).
                        placeholder(R.mipmap.img_shoppingmall_default_jd).load(item.getHead()).apply(options).into(mHead);

                img_recycler.setLayoutManager(new GridLayoutManager(ProxyDetailActivity.this, 3));
                img_recycler.setHasFixedSize(true);
                img_recycler.setNestedScrollingEnabled(false);
                BaseQuickAdapter<String, BaseViewHolder> adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.img_item, item.getImgs()) {
                    @Override
                    protected void convert(final BaseViewHolder helper, String item) {
                        Glide.with(ProxyDetailActivity.this).asBitmap().load(item)
                                .placeholder(R.mipmap.img_shoppingmall_default_jd)
                                .error(R.mipmap.error).into((ImageView) helper.getView(R.id.imageView));
                    }
                };
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    ArrayList<ThumbViewInfo> mThumbViewInfoList = new ArrayList<>();

                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ThumbViewInfo info;
                        mThumbViewInfoList.clear();
                        for (int i = 0; i < item.getImgs().size(); i++) {
                            Rect bounds = new Rect();
                            //new ThumbViewInfo(图片地址);
                            info = new ThumbViewInfo(item.getImgs().get(i));
                            info.setBounds(bounds);
                            mThumbViewInfoList.add(info);
                        }
                        GPreviewBuilder.from(ProxyDetailActivity.this)
                                //是否使用自定义预览界面，当然8.0之后因为配置问题，必须要使用
                                .to(GPreviewActivity.class)
                                .setData(mThumbViewInfoList)
                                .setCurrentIndex(position)
                                .setSingleFling(true)
                                .setType(GPreviewBuilder.IndicatorType.Number)
                                // 小圆点
                                //  .setType(GPreviewBuilder.IndicatorType.Dot)
                                .start();//启动

                    }
                });
                img_recycler.addItemDecoration(new SpaceItemDecoration(10));
                img_recycler.setAdapter(adapter);
            }
        };
        mShopRecycler.addItemDecoration(new SpaceItemDecoration(1, R.color.gray));
        mShopRecycler.setAdapter(mAdapter);
        mTitle.setBackClick(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_shop_detail;
    }

    public void setBanner(Banner banner, List<String> title, List<String> url) {
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                //设置图片加载器，图片加载器在下方
                .setImageLoader(new Imgloader())

                //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
                .setBannerAnimation(Transformer.Default)

                //设置轮播间隔时间
                .setDelayTime(3000)
                //设置是否为自动轮播，默认是“是”。
                .isAutoPlay(true)
                //设置指示器的位置，小点点，左中右。
                .setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                //设置轮播图的标题集合
//                .setBannerTitles(title)
                //设置图片网址或地址的集合
                .setImages(url).start();
        //必须最后调用的方法，启动轮播图。
    }


    @OnClick({R.id.add_comment, R.id.shop, R.id.shop_car, R.id.add_car, R.id.buy_now,
            R.id.room_num, R.id.room_type, R.id.time})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.room_type:
                final String[] strings = {
                        "钟点房",
                        "标准间",
                        "双人间",
                        "豪华间"
                };
                if (mRoomPicker == null) {
                    mRoomPicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            mRoomType.setText(strings[options1]);
                            mRoomPicker.dismiss();
                        }
                    }).build();
                    mRoomPicker.setNPicker(Arrays.asList(strings), null, null);
                }
                mRoomPicker.show();
                break;
            case R.id.time:
                if (mTimePickerView1 == null)
                    mTimePickerView1 = new TimePickerBuilder(this, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            if (mTimePickerView2 == null)
                                mTimePickerView2 = new TimePickerBuilder(ProxyDetailActivity.this, new OnTimeSelectListener() {
                                    @Override
                                    public void onTimeSelect(Date date, View v) {
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                                        mTime2 = dateFormat.format(date);
                                        mTime.setText(String.format("%s至%s", mTime1, mTime2));
                                    }
                                }).setTitleText("终止时间").build();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                            mTime1 = dateFormat.format(date);
                            mTimePickerView2.show();
                            if (mTime2 == null)
                                mTime2 = dateFormat.format(date);
                            mTime.setText(String.format("%s至%s", mTime1, mTime2));
                        }
                    }).setTitleText("起始时间").build();
                mTimePickerView1.show();
                break;
            case R.id.room_num:
                if (mDialog == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    View inflate = LayoutInflater.from(this).inflate(R.layout.modify_num, null);
                    mNum = inflate.findViewById(R.id.num);
                    mNum.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!"".equals(s.toString()) && Integer.valueOf(s.toString()) < 1) {
                                mNum.setText(String.valueOf(1));
                                mNum.setSelection(mNum.getText().length());
                                ToastUtil.showToast(ProxyDetailActivity.this, "请输入大于0的数字");
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });
                    mMinus_modify = inflate.findViewById(R.id.minus);
                    inflate.findViewById(R.id.minus).setOnClickListener(this);
                    inflate.findViewById(R.id.plus).setOnClickListener(this);
                    inflate.findViewById(R.id.cancel).setOnClickListener(this);
                    inflate.findViewById(R.id.sure).setOnClickListener(this);
                    builder.setView(inflate);
                    DisplayMetrics mMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
                    mDialog = builder.create();
                    WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
                    params.height = mMetrics.heightPixels / 3;
                }
                if (room_num == 1)
                    mMinus_modify.setImageResource(R.mipmap.minus_circle_disable);
                else mMinus_modify.setImageResource(R.mipmap.minus_circle_fill);
                mNum.setText(String.valueOf(room_num));
                mNum.setSelection(mNum.getText().length());
                mDialog.show();
                break;
            case R.id.add_comment:
                intent = new Intent(this, ReplyCommentActivity.class);
                intent.putExtra("type", Constant.REPLY_COMMENT);
                break;
            case R.id.shop:
                intent = new Intent(this, ShopActivity.class);
                finish();
                break;
            case R.id.shop_car:
                intent = new Intent(this, ShopCarActivity.class);
                intent.putExtra("type", Constant.CAR);
                break;
            case R.id.add_car:
                intent = new Intent(this, ShopCarActivity.class);
                intent.putExtra("type", Constant.ADD_CAR);
                break;
            case R.id.buy_now:
                intent = new Intent(this, ShopCarActivity.class);
                intent.putExtra("type", Constant.BUY_NOW);
                break;
        }
        if (intent != null)
            startActivity(intent);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minus:
                if (room_temp != 1) {
                    mNum.setText(String.valueOf(--room_temp));
                }
                if (room_temp == 1) {
                    mMinus_modify.setImageResource(R.mipmap.minus_circle_disable);
                }
                break;
            case R.id.plus:
                mNum.setText(String.valueOf(++room_temp));
                mMinus_modify.setImageResource(R.mipmap.minus_circle_fill);
                break;
            case R.id.cancel:
                mDialog.dismiss();
                break;
            case R.id.sure:
                mDialog.dismiss();
                room_num = room_temp;
                mRoomNum.setText(String.valueOf(room_num) + "间");
                break;
        }
    }
}
