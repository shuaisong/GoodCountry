package com.reeching.goodcountry.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.Contact.CommunityContact;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.activity.ArticleDetailActivity;
import com.reeching.goodcountry.base.BaseAddFragment;
import com.reeching.goodcountry.bean.ArticleBean1;
import com.reeching.goodcountry.bean.TwoCategoryBean;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerFragmentComponent;
import com.reeching.goodcountry.presenter.CommunityPresenter;
import com.reeching.goodcountry.util.ImgAdapterClick;
import com.reeching.goodcountry.util.LogUtil;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.ToastUtil;
import com.reeching.goodcountry.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class ArticleFragment extends BaseAddFragment implements CommunityContact.View {
    @Inject
    CommunityPresenter mCommunityPresenter;
    @BindView(R.id.articleSwip)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.disease)
    TextView mDisease;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.go)
    Button mGo;
    @BindView(R.id.line)
    LinearLayout mEmptyLine;
    @BindView(R.id.share)
    Button mShare_bt;
    private BaseQuickAdapter<ArticleBean1.DataBean.AritleArrBean, BaseViewHolder> mAdapter;
    private List<ArticleBean1.DataBean.AritleArrBean> mArticleBeans;
    private boolean mShare = false;
    private int pageNo = 0;
    private int type = 0;
    private String sickId;
    private String jbTypeId;
    private OptionsPickerView<String> mTypePicker;
    private TwoCategoryBean mTwoCategoryBean;
    private String mOpenId;

    @Override
    protected void setupFragComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder().appComponent(appComponent).build().inject(this);
    }

    /**
     * 加载数据
     */
    @Override
    protected void initData() {
        mSwipeRefreshLayout.measure(0, 0);
        mSwipeRefreshLayout.setRefreshing(true);
        mCommunityPresenter.getCategory();
        mCommunityPresenter.getArticle(1, pageNo,
                type, sickId, jbTypeId, mOpenId);
    }

    @Override
    protected void initView() {
        mCommunityPresenter.attachView(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mShare = bundle.getBoolean("share", false);
            mOpenId = bundle.getString("openId", "");
        }
        if (mShare) mShare_bt.setVisibility(View.VISIBLE);
        mArticleBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<ArticleBean1.DataBean.AritleArrBean, BaseViewHolder>(R.layout.article_item, mArticleBeans) {
            @Override
            protected void convert(BaseViewHolder helper, final ArticleBean1.DataBean.AritleArrBean item) {
                if (mShare) {
                    helper.getView(R.id.check).setVisibility(View.VISIBLE);
                    helper.setNestView(R.id.check);
                    helper.setImageResource(R.id.check, R.drawable.uncheck_circle);
                }
                ImageView head = helper.getView(R.id.head);
                RequestOptions options = new RequestOptions().circleCrop().
                        placeholder(R.mipmap.img_my_default_head)
                        .error(R.mipmap.img_my_default_head)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//                LogUtil.d(item.getTxlj());
                Glide.with(Objects.requireNonNull(ArticleFragment.this.getActivity())).asBitmap().load(Constant.IMG_URL + item.getTxlj()).apply(options).into(head);
                helper.setText(R.id.name, item.getPublishName());
                helper.setText(R.id.time, String.format("发表于%s", item.getCreatDate()));
                helper.setText(R.id.category, item.getWzType());
                helper.setText(R.id.content, item.getContent());
                helper.setText(R.id.comment, String.format(Locale.CHINESE, "(%s)", item.getAritleInfo().getCommentCount()));
                helper.setText(R.id.fabulous, String.format(Locale.CHINESE, "(%s)", item.getAritleInfo().getCollection()));
                helper.setText(R.id.collection, String.format(Locale.CHINESE, "(%s)", item.getAritleInfo().getCollection()));
                helper.setText(R.id.integral, String.format(Locale.CHINESE, "(%s)", item.getAritleInfo().getRewardCount()));
                if (item.getPicArray().size() > 0) {
                    final String picString = item.getPicArray().get(0).replace("[", "").replace("]", "").trim();
                    final String[] picS = picString.split(",");
                    RecyclerView img_recycler = helper.getView(R.id.img_recycler);
                    img_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                    img_recycler.setHasFixedSize(true);
                    img_recycler.setNestedScrollingEnabled(false);
                    BaseQuickAdapter<String, BaseViewHolder> adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.img_item, Arrays.asList(picS)) {
                        @Override
                        protected void convert(final BaseViewHolder helper, String item) {
                            LogUtil.d(item);
                            Glide.with(getActivity()).asBitmap()
                                    .placeholder(R.mipmap.img_shoppingmall_default_jd).error(R.mipmap.error)
                                    .load(Constant.IMG_URL + item)
                                    .into((ImageView) helper.getView(R.id.imageView));
                        }
                    };
                    img_recycler.addItemDecoration(new SpaceItemDecoration(10));
                    img_recycler.setAdapter(adapter);
                    adapter.setOnItemClickListener(new ImgAdapterClick(getActivity(), Arrays.asList(picS)));
                }
            }
        };
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ArticleBean1.DataBean.AritleArrBean articleBean = mArticleBeans.get(position);
                if (articleBean.isCheck()) {
                    articleBean.setCheck(false);
                    ((ImageView) view).setImageResource(R.drawable.uncheck_circle);
                } else {
                    articleBean.setCheck(true);
                    ((ImageView) view).setImageResource(R.drawable.check_circle);
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                intent.putExtra("article", mArticleBeans.get(position));
                startActivity(intent);
            }
        });
        mAdapter.setEnableLoadMore(true);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtil.d("onLoadMoreRequested");
                mSwipeRefreshLayout.setEnabled(false);
                mCommunityPresenter.getArticle(1, pageNo, type, sickId,
                        jbTypeId, mOpenId);
                ++pageNo;
            }
        }, mRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(1));
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mArticleBeans.clear();
                pageNo = 0;
                mCommunityPresenter.getArticle(1, pageNo, type, sickId, jbTypeId
                        , mOpenId);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser && mShare) {
            for (ArticleBean1.DataBean.AritleArrBean bean :
                    mArticleBeans) {
                if (bean.isCheck()) {
                    bean.setCheck(false);
                    BaseViewHolder viewHolder = (BaseViewHolder) mRecyclerView.findViewHolderForAdapterPosition(mArticleBeans.indexOf(bean));
                    if (viewHolder != null) {
                        viewHolder.setImageResource(R.id.check, R.drawable.uncheck_circle);
                    }
                }
            }
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_articl;
    }


    @OnClick({R.id.all_article, R.id.disease, R.id.type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_article:
                mType.setText(getActivity().getResources().getString(R.string.select_type));
                mDisease.setText(getActivity().getResources().getString(R.string.select_disease));
                pageNo = 0;
                sickId = "";
                jbTypeId = "";
                type = 0;
                mArticleBeans.clear();
                mSwipeRefreshLayout.setRefreshing(true);
                mCommunityPresenter.getArticle(
                        1, pageNo, type, sickId, jbTypeId, mOpenId);
                break;
            case R.id.disease:
                if (mTwoCategoryBean == null) {
                    ToastUtil.showToast(getActivity(), "加载类型中，请稍候。。。");
                } else {
                    setPicker();
                }
                break;
            case R.id.type:
                if (mTypePicker == null) {
                    mTypePicker = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            mType.setText(Constant.ARTICLE_CATEGORY[options1]);
                            type = ++options1;
                            pageNo = 0;
                            mArticleBeans.clear();
                            mSwipeRefreshLayout.setRefreshing(true);
                            mCommunityPresenter.getArticle(1, pageNo,
                                    type, sickId, jbTypeId, mOpenId);
                        }
                    }).setCancelColor(R.color.divider).setSubmitColor(R.color.green).build();
                    mTypePicker.setNPicker(Arrays.asList(Constant.ARTICLE_CATEGORY), null, null);
                }
                mTypePicker.show();
                break;
        }
    }

    private void setPicker() {
        final ArrayList<String> level1 = new ArrayList<>();

        final ArrayList<String> level2 = new ArrayList<>();
        final ArrayList<TwoCategoryBean.ObjectMultiArrayBean> level_2 = new ArrayList<>();
        for (TwoCategoryBean.ObjectMultiArrayBean bean :
                mTwoCategoryBean.getObjectMultiArray()) {
            if ("二级".equals(bean.getTypeLevel())) {
                level1.add(bean.getName());
            }
        }
        OptionsPickerView<String> picker1 = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {

            private OptionsPickerView<String> mPicker2;

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                level2.clear();
                level_2.clear();
                for (TwoCategoryBean.ObjectMultiArrayBean bean :
                        mTwoCategoryBean.getObjectMultiArray()) {
                    if (bean.getParentId().equals(mTwoCategoryBean.getObjectMultiArray().get(options1).getId())) {
                        level2.add(bean.getName());
                        level_2.add(mTwoCategoryBean.getObjectMultiArray().get(options1));
                    }
                }
                if (mPicker2 == null)
                    mPicker2 = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            mDisease.setText(level2.get(options1));
                            pageNo = 0;
                            sickId = level_2.get(options1).getParentId();
                            jbTypeId = level_2.get(options1).getId();
                            mArticleBeans.clear();
                            mSwipeRefreshLayout.setRefreshing(true);
                            mCommunityPresenter.getArticle(1, pageNo, type, sickId, jbTypeId, mOpenId);
                        }
                    }).setCancelColor(R.color.divider).setSubmitColor(R.color.green).build();
                mPicker2.setPicker(level2);
                mPicker2.show();
            }
        }).setCancelColor(R.color.divider).setSubmitColor(R.color.green).build();
        picker1.setPicker(level1);
        picker1.show();
    }

    @Override
    public void showArticle(List<ArticleBean1.DataBean.AritleArrBean> list, boolean loadSuccess) {
        if (!mSwipeRefreshLayout.isEnabled()) {
            mSwipeRefreshLayout.setEnabled(true);
        }
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
        }
        if (!loadSuccess) {
            mAdapter.loadMoreFail();
            return;
        }
        if (pageNo == 0 && list.size() == 0) {
            mEmptyLine.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
            return;
        } else mEmptyLine.setVisibility(View.GONE);
        mArticleBeans.addAll(list);
        if (list.size() < 10) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
        ++pageNo;
    }

    @Override
    public void showCategory(TwoCategoryBean bean) {
        mTwoCategoryBean = bean;
    }

    @Override
    public void start() {

    }

    @Override
    public void compete() {

    }
}
