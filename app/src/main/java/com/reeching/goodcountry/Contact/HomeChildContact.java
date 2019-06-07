package com.reeching.goodcountry.Contact;

import com.reeching.goodcountry.bean.ArticleBean1;

import java.util.List;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class HomeChildContact implements BaseContact {
    public interface View extends BaseView {
        void showArticle(List<ArticleBean1.DataBean.AritleArrBean> list, boolean loadSuccess);
    }

    public interface Presenter<T> extends BasePresenter<T> {
        /**
         * int wzWt 文章或者问题（1文章，2问题）
         * int pageNo当前页码
         * int pageSize 一页多少记录
         * int wzType 文章类型（1：疾病知识，2：病友经验，3：专家观点）
         */
        void getArticle(String url, int wzWt, int pageNo, int pageSize, int wzType);
    }
}
