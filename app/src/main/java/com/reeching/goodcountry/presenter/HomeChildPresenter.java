package com.reeching.goodcountry.presenter;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.reeching.goodcountry.BaseApplication;
import com.reeching.goodcountry.Contact.HomeChildContact;
import com.reeching.goodcountry.bean.ArticleBean1;
import com.reeching.goodcountry.util.JsonCallback;
import com.reeching.goodcountry.util.ToastUtil;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class HomeChildPresenter extends RxPresenter<HomeChildContact.View> implements HomeChildContact.Presenter<HomeChildContact.View> {
    @Inject
    HomeChildPresenter() {
    }


    @Override
    public void getArticle(String url, int wzWt, int pageNo, int pageSize, int wzType) {
        OkGo.<ArticleBean1>get(url)
                .params("wzwt", wzWt)
                .params("pageNo", pageNo)
                .params("pageSize", pageSize)
                .params("wzType", wzType)
                .execute(new JsonCallback<ArticleBean1>(ArticleBean1.class) {

                    @Override
                    public void onSuccess(Response<ArticleBean1> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getData() == null) {
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
}
