package com.reeching.goodcountry.presenter;


import android.app.Activity;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.reeching.goodcountry.Contact.HomeContact;
import com.reeching.goodcountry.bean.BannerBean;
import com.reeching.goodcountry.util.StringCallback;
import com.reeching.goodcountry.util.ToastUtil;

import javax.inject.Inject;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class HomePresenter extends RxPresenter<HomeContact.View> implements HomeContact.Presenter<HomeContact.View> {
    @Inject
    HomePresenter() {
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
