package com.reeching.goodcountry.presenter;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.Contact.MineContact;
import com.reeching.goodcountry.bean.MineNumBean;
import com.reeching.goodcountry.util.JsonCallback;
import com.reeching.goodcountry.util.PreferenceManager;

import javax.inject.Inject;

import static com.reeching.goodcountry.Constant.MYPAGECOUNT;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class MinePresenter extends RxPresenter<MineContact.View> implements MineContact.Presenter<MineContact.View> {
    @Inject
    MinePresenter() {
    }


    @Override
    public void getNum() {
        OkGo.<MineNumBean>get(Constant.BASE_URL + MYPAGECOUNT).cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .params("openId", PreferenceManager.getInstance().getString("openId"))
                .execute(new JsonCallback<MineNumBean>(MineNumBean.class) {
                    @Override
                    public void onSuccess(Response<MineNumBean> response) {
                        if (response.isSuccessful()) {
                            view.showNum(response.body());
                        }
                    }
                });
    }
}
