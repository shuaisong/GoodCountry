package com.reeching.goodcountry.component;

import com.reeching.goodcountry.base.BaseActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(BaseActivity activity);
    //    void inject(ShopDetailActivity activity);
//
//    void inject(ShopCarActivity shopCarActivity);
//
//    void inject(SureOrderActivity sureOrderActivity);
//
//    void inject(PayActivity payActivity);
//
//    void inject(LocationActivity locationActivity);
//
//    void inject(ReleaseArticleActivity releaseArticleActivity);
}
