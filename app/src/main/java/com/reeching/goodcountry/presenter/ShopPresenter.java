package com.reeching.goodcountry.presenter;


import android.app.Activity;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.Contact.ShopContact;
import com.reeching.goodcountry.bean.BannerBean;
import com.reeching.goodcountry.bean.HotelBean;
import com.reeching.goodcountry.bean.ShopBean;
import com.reeching.goodcountry.util.JsonCallback;
import com.reeching.goodcountry.util.StringCallback;
import com.reeching.goodcountry.util.ToastUtil;

import javax.inject.Inject;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class ShopPresenter extends RxPresenter<ShopContact.View> implements ShopContact.Presenter<ShopContact.View> {
    @Inject
    ShopPresenter() {
    }


    /**
     * 酒店
     */
    @Override
    public void getHotelList() {
        OkGo.<HotelBean>get(Constant.BASE_URL + Constant.HOTELLIST)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST).execute(new JsonCallback<HotelBean>(HotelBean.class) {

            @Override
            public void onSuccess(Response<HotelBean> response) {
                if (response.isSuccessful()) {
                    view.showHotelList(response.body().getJsonHot());
                } else {
                    ToastUtil.showToast((Activity) view, "网络错误");
                }
            }
        });
    }

    /**
     * 代诊
     * String shopShow:1(商场是否展示 0：不展示，1：展示)
     * String shopType:2 （展示端口分类 1：房间，2：代诊）
     *
     * @param shopShow
     * @param shopType
     */
    @Override
    public void getShopList(int shopShow, int shopType) {
        OkGo.<ShopBean>get(Constant.BASE_URL + Constant.SHOPLIST)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST).execute(new JsonCallback<ShopBean>(ShopBean.class) {

            @Override
            public void onSuccess(Response<ShopBean> response) {
                if (response.isSuccessful()) {
                    view.showShopList(response.body().getJsonShop());
                } else {
                    ToastUtil.showToast((Activity) view, "网络错误");
                }
            }
        });
    }

    @Override
    public void getBanner(String url) {
        OkGo.<String>get(url)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST).execute(new StringCallback() {

            @Override
            public void onSuccess(Response response) {
                if (response.body() != null) {
//                    LogUtil.d(response.body().toString());
                    String replace = response.body().toString()
                            .replace("\\\"", "").replace("\"[", "[")
                            .replace("]\"", "]")
                            .replace("src:", "\"src\":\"")
                            .replace(".jpg", ".jpg\"");
//                    LogUtil.d(replace);
                    BannerBean bannerBean = new Gson().fromJson(replace, BannerBean.class);
                    if (bannerBean.getCode() == 0) {
                        view.showBanner(bannerBean.getData());
                    } else {
                        ToastUtil.showToast((Activity) view, "网络错误");
                    }
                }
            }
        });
    }
}
