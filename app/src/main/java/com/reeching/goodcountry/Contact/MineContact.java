package com.reeching.goodcountry.Contact;

import com.reeching.goodcountry.bean.MineNumBean;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class MineContact implements BaseContact {
    public interface View extends BaseView {
        void showNum(MineNumBean numBean);
    }

    public interface Presenter<T> extends BasePresenter<T> {
        void getNum();
    }
}
