package com.reeching.goodcountry.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.Contact.HomeChildContact;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.activity.ArticleDetailActivity;
import com.reeching.goodcountry.activity.ReleaseArticleActivity;
import com.reeching.goodcountry.base.BasePageFragment;
import com.reeching.goodcountry.bean.ArticleBean1;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerFragmentComponent;
import com.reeching.goodcountry.presenter.HomeChildPresenter;
import com.reeching.goodcountry.util.ImgAdapterClick;
import com.reeching.goodcountry.util.LogUtil;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;

import static com.reeching.goodcountry.Constant.ARTICLE;

/**
 * Created by lenovo on 2019/1/24.
 * auther:lenovo
 * Date：2019/1/24
 */
public class HomeChildFragment extends BasePageFragment implements HomeChildContact.View {
    @Inject
    HomeChildPresenter mHomeChildPresenter;
    @BindView(R.id.disease_recycler)
    RecyclerView mDiseaseRecycler;
    @BindView(R.id.articleSwip)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private BaseQuickAdapter<ArticleBean1.DataBean.AritleArrBean, BaseViewHolder> mAdapter;
    private List<ArticleBean1.DataBean.AritleArrBean> mArticleBeans;
    private int pageNo = 0;
    private int mType;

    public static HomeChildFragment getInstance(int type) {
        HomeChildFragment fragment = new HomeChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    /**
     * 加载数据
     */
    @Override
    protected void initData() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.green, R.color.colorAccent);
        mSwipeRefreshLayout.measure(0, 0);
        mSwipeRefreshLayout.setRefreshing(true);
        mHomeChildPresenter.getArticle(Constant.BASE_URL + ARTICLE, 1, pageNo, 10, mType);
    }

    @Override
    protected void initView() {
        mHomeChildPresenter.attachView(this);
        Bundle arguments = getArguments();
        mType = 1;
        if (arguments != null) {
            mType = arguments.getInt("type", 1);
        }
        if (mType == 1) setUserVisibleHint(true);
        mArticleBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<ArticleBean1.DataBean.AritleArrBean, BaseViewHolder>(R.layout.article_item, mArticleBeans) {
            @Override
            protected void convert(BaseViewHolder helper, final ArticleBean1.DataBean.AritleArrBean item) {
                ImageView head = helper.getView(R.id.head);
                RequestOptions options = new RequestOptions().circleCrop().
                        placeholder(R.mipmap.img_my_default_head)
                        .error(R.mipmap.img_my_default_head)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//                LogUtil.d(item.getTxlj());
                Glide.with(Objects.requireNonNull(HomeChildFragment.this.getActivity())).asBitmap().load(Constant.IMG_URL + item.getTxlj()).apply(options).into(head);
                helper.setText(R.id.name, item.getPublishName());
                helper.setText(R.id.time, String.format("发表于%s", item.getCreatDate()));
                helper.setText(R.id.category, item.getWzType());
                helper.setText(R.id.content, item.getContent());
                helper.setText(R.id.comment, String.format(Locale.CHINESE, "(%s)", item.getAritleInfo().getCommentCount()));
                helper.setText(R.id.fabulous, String.format(Locale.CHINESE, "(%s)", item.getAritleInfo().getCollection()));
                helper.setText(R.id.collection, String.format(Locale.CHINESE, "(%s)", item.getAritleInfo().getCollection()));
                helper.setText(R.id.integral, String.format(Locale.CHINESE, "(%s)", item.getAritleInfo().getRewardCount()));
                List<String> picS = item.getPicArray();
                LogUtil.d("item.getPicArray()" + picS);
                LogUtil.d("item.getPicArray()" + picS.size());

                if (picS.size() > 0) {
                    RecyclerView img_recycler = helper.getView(R.id.img_recycler);
                    img_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                    img_recycler.setHasFixedSize(true);
                    img_recycler.setNestedScrollingEnabled(false);
                    BaseQuickAdapter<String, BaseViewHolder> adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.img_item, picS) {
                        @Override
                        protected void convert(final BaseViewHolder helper, String item) {
                            if (!item.startsWith("/")) {
                                item = "/" + item;
                            }
                            Glide.with(getActivity()).asBitmap()
                                    .placeholder(R.mipmap.img_shoppingmall_default_jd).error(R.mipmap.error)
                                    .load(Constant.IMG_URL + item)
                                    .into((ImageView) helper.getView(R.id.imageView));
                        }
                    };
                    img_recycler.addItemDecoration(new SpaceItemDecoration(10));
                    img_recycler.setAdapter(adapter);
                    adapter.setOnItemClickListener(new ImgAdapterClick(getActivity(), picS));
                }
            }
        };

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mDiseaseRecycler.setNestedScrollingEnabled(false);
        mDiseaseRecycler.setHasFixedSize(true);
        mDiseaseRecycler.setLayoutManager(manager);

        mDiseaseRecycler.addItemDecoration(new SpaceItemDecoration(1));
        mAdapter.setEnableLoadMore(true);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtil.d("onLoadMoreRequested");
                mSwipeRefreshLayout.setEnabled(false);
                mHomeChildPresenter.getArticle(Constant.BASE_URL + ARTICLE, 1, pageNo, 10, 1);
                ++pageNo;
            }
        }, mDiseaseRecycler);

        mDiseaseRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                intent.putExtra("article", mArticleBeans.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_disease;
    }


    @Override
    public void showArticle(List<ArticleBean1.DataBean.AritleArrBean> list, boolean loadSuccess) {
        mSwipeRefreshLayout.setEnabled(false);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
        }
        if (!loadSuccess) {
            mAdapter.loadMoreFail();
            return;
        }
        if (pageNo == 0 && list.size() == 0) {
            mAdapter.loadMoreEnd();
            View empty = LayoutInflater.from(getActivity()).inflate(R.layout.article_empty, null);
            empty.findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().startActivity(new Intent(getActivity(), ReleaseArticleActivity.class));
                }
            });

            mAdapter.setEmptyView(empty);
            return;
        }
        mArticleBeans.addAll(list);
        if (list.size() < 10) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
        ++pageNo;
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }
}
