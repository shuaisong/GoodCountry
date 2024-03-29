package com.reeching.goodcountry.presenter;

import com.reeching.goodcountry.Contact.BaseContact;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class RxPresenter<T extends BaseContact.BaseView> implements BaseContact.BasePresenter<T> {
    T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
