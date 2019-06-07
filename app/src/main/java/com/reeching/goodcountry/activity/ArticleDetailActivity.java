package com.reeching.goodcountry.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.bean.ArticleBean1;
import com.reeching.goodcountry.bean.CommentBean;
import com.reeching.goodcountry.util.ImgAdapterClick;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.StatusBarUtil;
import com.reeching.goodcountry.view.ArticleCommentPop;
import com.reeching.goodcountry.view.DrawableCenterTextView;
import com.reeching.goodcountry.view.TitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleDetailActivity extends AppCompatActivity {

    @BindView(R.id.head)
    ImageView mHead;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.content)
    TextView mContent;
    @BindView(R.id.comment)
    DrawableCenterTextView mComment;
    @BindView(R.id.fabulous)
    DrawableCenterTextView mFabulous;
    @BindView(R.id.collection)
    DrawableCenterTextView mCollection;
    @BindView(R.id.integral)
    DrawableCenterTextView mIntegral;
    @BindView(R.id.line1)
    LinearLayout mLine1;
    @BindView(R.id.add_comment)
    ImageView mAddComment;
    @BindView(R.id.comment_recycler)
    RecyclerView mCommentRecycler;
    @BindView(R.id.article_recycler)
    RecyclerView mArticleRecycler;
    @BindView(R.id.title)
    TitleView mTitle;
    @BindView(R.id.level)
    TextView mLevel;
    @BindView(R.id.parent)
    ScrollView mParent;
    private BaseQuickAdapter<CommentBean, BaseViewHolder> mAdapter;
    private List<CommentBean> mCommentBeans;
    private BaseQuickAdapter<String, BaseViewHolder> mImg_adapter;
    private ArrayList<String> mArticleImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        ButterKnife.bind(this);
        initView();
        iniData();
    }

    private void iniData() {
        Intent intent = getIntent();
        ArticleBean1.DataBean.AritleArrBean bean = (ArticleBean1.DataBean.AritleArrBean) intent.getSerializableExtra("article");
        mFabulous.setText(bean.getAritleInfo().getPraiseCount());
        mIntegral.setText(bean.getAritleInfo().getRewardCount());
        mComment.setText(bean.getAritleInfo().getCommentCount());
        mCollection.setText(bean.getAritleInfo().getCollection());
        mName.setText(bean.getPublishName());
        mDate.setText(String.format("发表于：%s", bean.getCreatDate()));
        mContent.setText(bean.getContent());
        Glide.with(this).asBitmap().circleCrop().load(Constant.IMG_URL + bean.getTxlj()).placeholder(R.mipmap.img_my_default_head).into(mHead);
        mTitle.setTitle(bean.getTile());
        mArticleImg.addAll(bean.getPicArray());
        mImg_adapter.notifyDataSetChanged();
        mTitle.setBackClick(this);
    }

    private void initView() {
        mCommentRecycler.setHasFixedSize(true);
        mCommentRecycler.setNestedScrollingEnabled(false);
        mArticleRecycler.setHasFixedSize(true);
        mArticleRecycler.setNestedScrollingEnabled(false);
        mArticleRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        mCommentRecycler.setLayoutManager(new LinearLayoutManager(this));
        mCommentBeans = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<CommentBean, BaseViewHolder>(R.layout.article_comment, mCommentBeans) {

            @Override
            protected void convert(BaseViewHolder helper, CommentBean item) {
                RecyclerView recycler = helper.getView(R.id.comment_item);
                ImageView mHead = helper.getView(R.id.head);
                recycler.setHasFixedSize(true);
                recycler.setNestedScrollingEnabled(false);
                helper.setText(R.id.name, item.getName());
                helper.setText(R.id.time, item.getDate());
                helper.setText(R.id.content, item.getContent());
                helper.setNestView(R.id.more);
                Glide.with(ArticleDetailActivity.this).asBitmap().circleCrop()
                        .load(item.getHead()).placeholder(R.mipmap.img_my_default_head).into(mHead);
                recycler.setLayoutManager(new LinearLayoutManager(ArticleDetailActivity.this));
                BaseQuickAdapter<CommentBean.Comment2, BaseViewHolder> comment2BaseViewHolderBaseQuickAdapter =
                        new BaseQuickAdapter<CommentBean.Comment2, BaseViewHolder>(R.layout.article_comment, item.getComment2s()) {

                            @Override
                            protected void convert(BaseViewHolder helper, CommentBean.Comment2 item) {
                                helper.setText(R.id.name, item.getName());
                                helper.setText(R.id.time, item.getDate());
                                helper.setText(R.id.content, item.getContent());
                                ImageView mHead = helper.getView(R.id.head);
                                helper.setNestView(R.id.more);
                                Glide.with(ArticleDetailActivity.this).asBitmap().load(item.getHead())
                                        .placeholder(R.mipmap.img_my_default_head).circleCrop().into(mHead);

                            }
                        };
                comment2BaseViewHolderBaseQuickAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {

                    private PopupWindow mPopupWindow;

                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.getId() == R.id.more) {
                            if (mPopupWindow == null) {
                                ArticleCommentPop articleCommentPop = new ArticleCommentPop(ArticleDetailActivity.this, false);
                                mPopupWindow = articleCommentPop.getInstance();
                            }
                            mPopupWindow.showAsDropDown(view, -40, 0, Gravity.START);
                        }
                    }
                });
                recycler.addItemDecoration(new SpaceItemDecoration(1, R.color.gray));
                recycler.setAdapter(comment2BaseViewHolderBaseQuickAdapter);
            }
        };
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            private PopupWindow mPopupWindow;

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.more) {
                    if (mPopupWindow == null) {
                        ArticleCommentPop mArticleCommentPop = new ArticleCommentPop(ArticleDetailActivity.this, true);
                        mPopupWindow = mArticleCommentPop.getInstance();
                        mArticleCommentPop.setRewardClickLocation(mParent);
                    }
                    mPopupWindow.showAsDropDown(view, -40, 0, Gravity.START);
                }
            }
        });
        mCommentRecycler.addItemDecoration(new SpaceItemDecoration(1, R.color.gray));
        mCommentRecycler.setAdapter(mAdapter);
        mArticleImg = new ArrayList<>();
        mImg_adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.img_item, mArticleImg) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                Glide.with(ArticleDetailActivity.this).asBitmap().load(R.mipmap.img_shoppingmall_default_jd)
                        .error(R.mipmap.error).load(Constant.IMG_URL + item).into((ImageView) helper.getView(R.id.imageView));
            }
        };
        mImg_adapter.setOnItemClickListener(new ImgAdapterClick(this, mArticleImg));

        mArticleRecycler.setAdapter(mImg_adapter);
    }

    @OnClick({R.id.comment, R.id.fabulous, R.id.collection, R.id.integral, R.id.add_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fabulous:
                mFabulous.setText(String.format(Locale.CHINESE, "(%d)", Integer.valueOf(mFabulous.getText().toString().trim()) + 1));
                mFabulous.setClickable(false);
                break;
            case R.id.collection:
                mCollection.setText(String.format(Locale.CHINESE, "(%d)", Integer.valueOf(mCollection.getText().toString().trim()) + 1));
                mCollection.setClickable(false);
                break;
            case R.id.integral:
                mIntegral.setText(String.format(Locale.CHINESE, "(%d)", Integer.valueOf(mIntegral.getText().toString().trim()) + 1));
                mIntegral.setClickable(false);
                break;
            case R.id.add_comment:
                Intent intent = new Intent(this, ReplyCommentActivity.class);
                intent.putExtra("type", Constant.RELEASE_COMMENT);
                startActivity(intent);
                break;
        }
    }
}
