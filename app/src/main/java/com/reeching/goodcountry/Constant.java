package com.reeching.goodcountry;

/**
 * Created by lenovo on 2019/1/24.
 * auther:lenovo
 * Date：2019/1/24
 */
public class Constant {
    public static final int[] imgs_Category = {R.mipmap.button_home_01,
            R.mipmap.button_home_02,
            R.mipmap.button_home_03,
            R.mipmap.button_home_04,
            R.mipmap.button_home_05,
            R.mipmap.button_home_06,
            R.mipmap.button_home_07,
            R.mipmap.button_home_08,
    };
    public static final String[] titles_Category = {"生殖器", "髓母瘤", "胶质瘤", "淋巴瘤", "转移瘤", "室管膜瘤", "脑膜瘤", "其他肿瘤"};
    public static final String[] Shop_titles_Category = {"酒店", "代诊", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"};
    public static final String[] hotel_imgs = {
            "http://dimg12.c-ctrip.com/images/200q0w000000k8x9oAECB_R_130_130.jpg",
            "http://dimg11.c-ctrip.com/images/hotel/53000/52741/941abc93388f496cb660691cf8b48bde_R_130_130.jpg",
            "http://dimg13.c-ctrip.com/images/hotel/46000/45727/0BCF85B2-9802-42A6-AC1E-7D9CEC530374_R_130_130.jpg",
            "http://dimg12.c-ctrip.com/images/20070e00000075s8u5D03_R_130_130.jpg",
            "http://dimg12.c-ctrip.com/images/200q0w000000k8x9oAECB_R_130_130.jpg",
            "http://dimg11.c-ctrip.com/images/hotel/53000/52741/941abc93388f496cb660691cf8b48bde_R_130_130.jpg",
            "http://dimg13.c-ctrip.com/images/hotel/46000/45727/0BCF85B2-9802-42A6-AC1E-7D9CEC530374_R_130_130.jpg",
            "http://dimg12.c-ctrip.com/images/20070e00000075s8u5D03_R_130_130.jpg"
    };
    public static final String[] proxy_imgs = {
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=701943095,3064279922&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=185478631,2252467481&fm=27&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1695768554,2065576622&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=701943095,3064279922&fm=27&gp=0.jpg"
    };
    public static final String[] Order_type = {
            "全部",
            "待付款",
            "待发货",
            "待评价"
    };
    public static final String[] CITIES = {
            "北京xxxxxxxx",
            "上海",
            "广州",
            "郑州",
            "西安",
            "杭州",
            "深圳"
    };


    public static final String head_path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548319382466&di=ebd7e21b73c2e5bf1f36133ace81f048&imgtype=0&src=http%3A%2F%2Fs7.sinaimg.cn%2Fmiddle%2F4b916806x7525f6e44bc6%26690";

//    public static final String BASE_URL = "http://192.168.2.25:8080/diai/f";
    public static final String BASE_URL = "https://www.diaiyiliao.cn:8443/diai/f";
    //    图片
//    public static final String IMG_URL = "http://192.168.2.25:8080";
    public static final String IMG_URL = "https://www.diaiyiliao.cn:8443";
    //轮播
    public static final String BANNER = "/front/aritle/swiperList";
    //文章
    public static final String ARTICLE = "/front/aritle/getQues";
    //    分类
    public static final String TwoCategory = "/sickType/jbTypePicker";
    //    酒店表
    public static final String HOTELLIST = "/shopping/getHotelList";
    //    代诊
    public static final String SHOPLIST = "/shopping/getShopList";
    //    病例和订单数量
    public static final String MYPAGECOUNT = "/front/other/myPageCount";
    //    文章收藏
    public static final String ARITLECOLLECTION = "/front/aritle/getColAritle";
    //    优惠券
    public static final String COUPON = "/front/user/getMyCoupon";
    //发布文章、问题
    public static final String PUBLISHARTICLE = "/front/aritle/publishAritle";
    //邀请好友统计
    public static final String FRIENDCOUNT = "/front/user/friendCount";


    public static final int HOME_DISEASE_FRAGMENT = 1;
    public static final int HOME_EXPERIENCE_FRAGMENT = 2;
    public static final int HOME_OPINION_FRAGMENT = 3;

    public static final int AUTHENTICATION_DOCTOR = 101;
    public static final int AUTHENTICATION_PATIENT = 102;
    public static final int ADDCASE = 103;
    public static final int GOSHOP = 104;
    public static final int REQUEST_CODE_CHOOSE = 105;
    public static final int Location_CODE = 106;
    public static final int RELEASE_COMMENT = 4;
    public static final int REPLY_COMMENT = 5;
    public static final int CAR = 6;
    public static final int BUY_NOW = 7;
    public static final int ADD_CAR = 8;
    public static final String[] ARTICLE_CATEGORY = {"疾病知识", "病友经验", "专家观点"};
    public static final String[] ARTICLE_COLLECTION = {
            "收藏夹1", "收藏夹2", "收藏夹3","收藏夹4","收藏夹5","收藏夹6"
    };

    public static final String[] COUPON_TYPE = {"代金券", "折扣券"};
}
