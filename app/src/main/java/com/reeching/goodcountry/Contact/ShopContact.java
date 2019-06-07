package com.reeching.goodcountry.Contact;

import com.reeching.goodcountry.bean.BannerBean;
import com.reeching.goodcountry.bean.HotelBean;
import com.reeching.goodcountry.bean.ShopBean;

import java.util.List;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class ShopContact implements BaseContact {
    public interface View extends BaseView {
        void showHotelList(List<HotelBean.JsonHotBean> beans);

        void showShopList(List<ShopBean.JsonShopBean> beans);

        void showBanner(List<BannerBean.DataBean> list);

    }

    public interface Presenter<T> extends BasePresenter<T> {
        /**
         * 酒店
         */
        void getHotelList();

        /**
         * 代诊
         * String shopShow:1(商场是否展示 0：不展示，1：展示)
         * String shopType:2 （展示端口分类 1：房间，2：代诊）
         */
        void getShopList(int shopShow, int shopType);

        void getBanner(String url);
    }
}
