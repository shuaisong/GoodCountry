package com.reeching.goodcountry.component;


import com.reeching.goodcountry.fragment.ArticleCollectionFragment;
import com.reeching.goodcountry.fragment.ArticleFragment;
import com.reeching.goodcountry.fragment.Home1Fragment;
import com.reeching.goodcountry.fragment.HomeChildFragment;
import com.reeching.goodcountry.fragment.HomeFragment;
import com.reeching.goodcountry.fragment.MineFragment;
import com.reeching.goodcountry.fragment.OrderFragment;
import com.reeching.goodcountry.fragment.ReleaseFragment;
import com.reeching.goodcountry.fragment.ShopFragment;

import dagger.Component;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {

    void inject(ArticleCollectionFragment articleCollectionFragment);

    void inject(ArticleFragment articleFragment);

    void inject(HomeChildFragment homeChildFragment);
//
    void inject(HomeFragment homeFragment);
    void inject(Home1Fragment homeFragment);

    void inject(OrderFragment orderFragment);

    void inject(ShopFragment shopFragment);

    void inject(MineFragment mineFragment);

    void inject(ReleaseFragment releaseFragment);
//    void inject(BasePageFragment fragment);
}
