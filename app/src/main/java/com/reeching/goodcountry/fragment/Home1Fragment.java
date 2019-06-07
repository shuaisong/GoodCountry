package com.reeching.goodcountry.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.Contact.HomeContact;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.activity.MainActivity;
import com.reeching.goodcountry.adapter.HomeCategoryAdapter;
import com.reeching.goodcountry.adapter.VPAdapter;
import com.reeching.goodcountry.base.BaseAddFragment;
import com.reeching.goodcountry.bean.BannerBean;
import com.reeching.goodcountry.bean.HomeCategoryBean;
import com.reeching.goodcountry.bean.HomeErrorBean;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerFragmentComponent;
import com.reeching.goodcountry.presenter.HomePresenter;
import com.reeching.goodcountry.util.Imgloader;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.reeching.goodcountry.Constant.imgs_Category;
import static com.reeching.goodcountry.Constant.titles_Category;

/**
 * Created by lenovo on 2019/1/23.
 * auther:lenovo
 * Date：2019/1/23
 */
public class Home1Fragment extends BaseAddFragment implements HomeContact.View {
    @Inject
    HomePresenter mHomePresenter;
    @BindView(R.id.appbar)
    AppBarLayout mBarLayout;
    @BindView(R.id.category)
    RecyclerView mCategory;
    @BindView(R.id.banner)
    Banner mBanner;
    List<String> titles = new ArrayList<>();
    List<String> imgs = new ArrayList<>();
    List<HomeCategoryBean> mBeans = new ArrayList<>();
    List<HomeErrorBean> mErrorBeans = new ArrayList<>();
    @BindView(R.id.error_recycler)
    RecyclerView mErrorRecycler;
    @BindView(R.id.left)
    ImageView mLeft;
    @BindView(R.id.right)
    ImageView mRight;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.home_vp)
    ViewPager mHomeVp;

    {
        for (int i = 0; i < 8; i++) {
            titles.add("分类");
            HomeCategoryBean bean = new HomeCategoryBean();
            bean.setPath(imgs_Category[i]);
            bean.setTitle(titles_Category[i]);
            mBeans.add(bean);
            HomeErrorBean errorBean = new HomeErrorBean();
            errorBean.setContent("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + i);
            errorBean.setTitle("xxxxxx" + i);
            mErrorBeans.add(errorBean);
        }
    }

    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);

    }

    @Override
    protected void initData() {
        mHomePresenter.getBanner(Constant.BASE_URL + Constant.BANNER);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home1;
    }

    @Override
    protected void initView() {
        mHomePresenter.attachView(this);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        mCategory.setLayoutManager(manager);
        HomeCategoryAdapter homeCategoryAdapter = new HomeCategoryAdapter(R.layout.home_category_item, mBeans);
        mCategory.setHasFixedSize(true);
        mCategory.setNestedScrollingEnabled(false);
        mCategory.addItemDecoration(new SpaceItemDecoration(10));
        mCategory.setAdapter(homeCategoryAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        mErrorRecycler.setLayoutManager(linearLayoutManager);
        BaseQuickAdapter<HomeErrorBean, BaseViewHolder> quickAdapter =
                new BaseQuickAdapter<HomeErrorBean, BaseViewHolder>(R.layout.home_error_item, mErrorBeans) {
                    @Override
                    protected void convert(BaseViewHolder helper, HomeErrorBean item) {
                        helper.setText(R.id.title, item.getTitle());
                        helper.setText(R.id.content, item.getContent());
                    }
                };
        mErrorRecycler.setNestedScrollingEnabled(false);
        mErrorRecycler.setHasFixedSize(true);
        mErrorRecycler.setAdapter(quickAdapter);
        mErrorRecycler.scrollToPosition(mErrorBeans.size() / 2);
        HomeChildFragment diseaseFragment = HomeChildFragment.getInstance(Constant.HOME_DISEASE_FRAGMENT);
        HomeChildFragment experienceFragment = HomeChildFragment.getInstance(Constant.HOME_EXPERIENCE_FRAGMENT);
        HomeChildFragment opinionFragment = HomeChildFragment.getInstance(Constant.HOME_OPINION_FRAGMENT);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(diseaseFragment);
        fragments.add(experienceFragment);
        fragments.add(opinionFragment);
        mHomeVp.setOffscreenPageLimit(3);
        VPAdapter vpAdapter = new VPAdapter(getChildFragmentManager(), fragments, Constant.ARTICLE_CATEGORY);
        mHomeVp.setAdapter(vpAdapter);
        mHomeVp.setCurrentItem(0);
        mTab.setupWithViewPager(mHomeVp);

    }

    public void setBanner(Banner banner, List<String> title, List<String> url) {
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                //设置图片加载器，图片加载器在下方
                .setImageLoader(new Imgloader())

                //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
                .setBannerAnimation(Transformer.Default)

                //设置轮播间隔时间
                .setDelayTime(3000)
                //设置是否为自动轮播，默认是“是”。
                .isAutoPlay(true)
                //设置指示器的位置，小点点，左中右。
                .setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                //设置轮播图的标题集合
//                .setBannerTitles(title)
                //设置图片网址或地址的集合
                .setImages(url).start();
        //必须最后调用的方法，启动轮播图。
    }

    private int position = mErrorBeans.size() / 2;

    @OnClick({R.id.left, R.id.right, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                mErrorRecycler.scrollToPosition(--position);
                if (position <= 0) {
                    mLeft.setVisibility(View.GONE);
                }
                if (mRight.getVisibility() == View.GONE) {
                    mRight.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.right:
                mErrorRecycler.scrollToPosition(++position);
                if (position >= mErrorBeans.size() - 1) {
                    mRight.setVisibility(View.GONE);
                }
                if (mLeft.getVisibility() == View.GONE) {
                    mLeft.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.more:
                ((MainActivity) getActivity()).setRadioCheck(R.id.community);
                break;
        }
    }

    @Override
    public void showBanner(List<BannerBean.DataBean> list) {
        for (BannerBean.DataBean dataBean : list) {
            imgs.add(Constant.IMG_URL + dataBean.getSrc());
        }
        setBanner(mBanner, null, imgs);
    }

    @Override
    public void start() {
    }

    @Override
    public void compete() {

    }
}
