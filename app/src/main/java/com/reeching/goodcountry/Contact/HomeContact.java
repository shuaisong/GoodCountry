package com.reeching.goodcountry.Contact;

import com.reeching.goodcountry.bean.BannerBean;

import java.util.List;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class HomeContact implements BaseContact {
    public interface View extends BaseContact.BaseView {
        void showBanner(List<BannerBean.DataBean> list);
    }

    public interface Presenter<T> extends BaseContact.BasePresenter<T> {
        void getBanner(String url);
    }
}
