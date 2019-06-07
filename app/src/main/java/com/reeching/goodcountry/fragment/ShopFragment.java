package com.reeching.goodcountry.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.Contact.ShopContact;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.activity.HotelDetailActivity;
import com.reeching.goodcountry.activity.LocationActivity;
import com.reeching.goodcountry.activity.ProxyDetailActivity;
import com.reeching.goodcountry.activity.ShopActivity;
import com.reeching.goodcountry.base.BaseAddFragment;
import com.reeching.goodcountry.bean.BannerBean;
import com.reeching.goodcountry.bean.HotelBean;
import com.reeching.goodcountry.bean.ShopBean;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerFragmentComponent;
import com.reeching.goodcountry.presenter.ShopPresenter;
import com.reeching.goodcountry.util.Imgloader;
import com.reeching.goodcountry.util.LocationUtil;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/1/23.
 * auther:lenovo
 * Date：2019/1/23
 */
public class ShopFragment extends BaseAddFragment implements BaseQuickAdapter.OnItemClickListener, ShopContact.View {
    @Inject
    ShopPresenter mShopPresenter;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.city)
    TextView mCity;
    @BindView(R.id.hotel)
    RecyclerView mHotel;
    @BindView(R.id.proxy)
    RecyclerView mProxy;
    Unbinder unbinder;
    private BaseQuickAdapter<HotelBean.JsonHotBean, BaseViewHolder> mAdapter;
    private BaseQuickAdapter<ShopBean.JsonShopBean, BaseViewHolder> mAdapter_proxy;
    private List<HotelBean.JsonHotBean> mHotelBeans;
    private List<ShopBean.JsonShopBean> mProxybean;
    List<String> imgs = new ArrayList<>();

    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    protected void initData() {
        LocationUtil.getLocation(mCity);
        mShopPresenter.getBanner(Constant.BASE_URL + Constant.BANNER);
        mShopPresenter.getHotelList();
        mShopPresenter.getShopList(1, 2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PackageManager.PERMISSION_GRANTED == grantResults[0]) {
            LocationUtil.getLocation(mCity);
        }
    }

    @Override
    protected void initView() {
        mShopPresenter.attachView(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mHotel.setLayoutManager(gridLayoutManager);
        mHotelBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<HotelBean.JsonHotBean, BaseViewHolder>(R.layout.shop_hotel_item, mHotelBeans) {
            @Override
            protected void convert(BaseViewHolder helper, HotelBean.JsonHotBean item) {
                helper.setText(R.id.title, item.getHotelName());
                helper.setText(R.id.price, String.format("$%s/天", item.getHotelPrice()));
                RequestOptions options = new RequestOptions().fitCenter();
                Glide.with(getActivity()).load(Constant.IMG_URL + item.getPhoto()).apply(options)
                        .placeholder(R.mipmap.img_shoppingmall_default_jd).error(R.mipmap.error).into((ImageView) helper.getView(R.id.img));
            }
        };
        mAdapter.setOnItemClickListener(this);
        mHotel.setHasFixedSize(true);
        mHotel.setNestedScrollingEnabled(false);
        mHotel.addItemDecoration(new SpaceItemDecoration(10));
        mHotel.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager_proxy = new GridLayoutManager(getActivity(), 2);
        mProxy.setLayoutManager(gridLayoutManager_proxy);
        mProxybean = new ArrayList<>();
        mAdapter_proxy = new BaseQuickAdapter<ShopBean.JsonShopBean, BaseViewHolder>(R.layout.shop_hotel_item, mProxybean) {
            @Override
            protected void convert(BaseViewHolder helper, ShopBean.JsonShopBean item) {
                helper.setText(R.id.title, item.getShopName());
                helper.setText(R.id.price, String.format("$%s/次", item.getShopDabPrice()));
                Glide.with(getActivity()).load(Constant.IMG_URL + item.getPhoto())
                        .fitCenter()
                        .error(R.mipmap.error).placeholder(R.mipmap.img_shoppingmall_default_jd)
                        .into((ImageView) helper.getView(R.id.img));
            }
        };
        mAdapter_proxy.setOnItemClickListener(this);
        mProxy.setHasFixedSize(true);
        mProxy.setNestedScrollingEnabled(false);
        mProxy.addItemDecoration(new SpaceItemDecoration(10));
        mProxy.setAdapter(mAdapter_proxy);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_shop;
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

    @OnClick({R.id.more_shop, R.id.city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more_shop:
                startActivity(new Intent(getActivity(), ShopActivity.class));
                break;
            case R.id.city:
                startActivityForResult(new Intent(getActivity(), LocationActivity.class), Constant.Location_CODE);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.Location_CODE && resultCode == Activity.RESULT_OK) {
            String city = data.getStringExtra("city");
            mCity.setText(city);
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent;
        if (adapter == mAdapter) {
            intent = new Intent(getActivity(), HotelDetailActivity.class);
            intent.putExtra("good", mHotelBeans.get(position));
        } else {
            intent = new Intent(getActivity(), ProxyDetailActivity.class);
            intent.putExtra("good", mProxybean.get(position));
        }
        startActivity(intent);
    }

    @Override
    public void showHotelList(List<HotelBean.JsonHotBean> beans) {
        mHotelBeans.clear();
        mHotelBeans.addAll(beans);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showShopList(List<ShopBean.JsonShopBean> beans) {
        mProxybean.clear();
        mProxybean.addAll(beans);
        mAdapter_proxy.notifyDataSetChanged();
    }

    @Override
    public void showBanner(List<BannerBean.DataBean> list) {
        for (BannerBean.DataBean dataBean : list) {
            imgs.add(Constant.IMG_URL + dataBean.getSrc());
        }
        setBanner(mBanner, null, imgs);
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }
}
