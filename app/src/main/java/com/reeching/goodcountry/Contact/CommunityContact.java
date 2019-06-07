package com.reeching.goodcountry.Contact;

import com.reeching.goodcountry.bean.ArticleBean1;
import com.reeching.goodcountry.bean.TwoCategoryBean;

import java.util.List;

/**
 * Created by lenovo on 2019/3/1.
 * auther:lenovo
 * Date：2019/3/1
 */
public class CommunityContact implements BaseContact {
    public interface View extends BaseView {
        void showArticle(List<ArticleBean1.DataBean.AritleArrBean> list, boolean loadSuccess);

        void showCategory(TwoCategoryBean bean);
    }

    public interface Presenter<T> extends BasePresenter<T> {
        /**
         * int wzWt 文章或者问题（1文章，2问题）
         * int pageNo当前页码
         * <p>
         * int wzType 文章类型（1：疾病知识，2：病友经验，3：专家观点）
         * String sickId 病症二级分类id（可选）
         * String jbTypeId 疾病三级分类id（可选）
         * String openId
         */
        void getArticle(int wzWt, int pageNo, int wzType,
                        String sickId, String jbTypeId, String openId);

        void getCategory();
    }
}
