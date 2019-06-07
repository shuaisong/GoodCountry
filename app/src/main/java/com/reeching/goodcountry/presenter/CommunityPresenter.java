package com.reeching.goodcountry.presenter;


import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.reeching.goodcountry.BaseApplication;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.Contact.CommunityContact;
import com.reeching.goodcountry.bean.ArticleBean1;
import com.reeching.goodcountry.bean.TwoCategoryBean;
import com.reeching.goodcountry.util.JsonCallback;
import com.reeching.goodcountry.util.ToastUtil;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.reeching.goodcountry.Constant.ARTICLE;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class CommunityPresenter extends RxPresenter<CommunityContact.View> implements CommunityContact.Presenter<CommunityContact.View> {
    @Inject
    CommunityPresenter() {
    }


    @Override
    public void getArticle(int wzWt, int pageNo, int wzType, String sickId,
                           String jbTypeId, String openId) {
        String url;
        if (openId == null)
            url = Constant.BASE_URL + ARTICLE;
        else
            url = Constant.BASE_URL + Constant.ARITLECOLLECTION;
        GetRequest<ArticleBean1> params = OkGo.<ArticleBean1>get(url)
                .tag(this)
                .params("wzwt", wzWt)
                .params("pageNo", pageNo)
                .params("pageSize", 10);
        if (wzType != 0) {
            params.params("wzType", wzType);
        }
        if (TextUtils.isEmpty(sickId)) {
            params.params("sickId", sickId);
        }
        if (TextUtils.isEmpty(jbTypeId)) {
            params.params("jbTypeId", jbTypeId);
        }
        if (TextUtils.isEmpty(jbTypeId)) {
            params.params("openId", openId);
        }
        params.execute(new JsonCallback<ArticleBean1>(ArticleBean1.class) {

            @Override
            public void onSuccess(Response<ArticleBean1> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null || response.body().getData() == null) {
                        view.showArticle(new ArrayList<ArticleBean1.DataBean.AritleArrBean>(), true);
                    } else
                        view.showArticle(response.body().getData().getAritleArr(), true);
                } else {
                    ToastUtil.showToast(BaseApplication.getInstance(), "数据请求失败");
                    view.showArticle(new ArrayList<ArticleBean1.DataBean.AritleArrBean>(), false);
                }
            }

            @Override
            public void onError(Response<ArticleBean1> response) {
                super.onError(response);
                view.showArticle(new ArrayList<ArticleBean1.DataBean.AritleArrBean>(), false);
            }
        });

    }

    @Override
    public void getCategory() {
        OkGo.<TwoCategoryBean>get(Constant.BASE_URL + Constant.TwoCategory)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .execute(new JsonCallback<TwoCategoryBean>(TwoCategoryBean.class) {

                    @Override
                    public void onSuccess(Response<TwoCategoryBean> response) {
                        if (response.isSuccessful()) {
                            view.showCategory(response.body());
                        }
                    }
                });
    }
}
