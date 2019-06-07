package com.reeching.goodcountry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.base.BaseActivity;
import com.reeching.goodcountry.bean.ReleaseArticle;
import com.reeching.goodcountry.bean.TwoCategoryBean;
import com.reeching.goodcountry.component.AppComponent;
import com.reeching.goodcountry.component.DaggerActivityComponent;
import com.reeching.goodcountry.util.GlideEngine;
import com.reeching.goodcountry.util.ImgAdapterClick;
import com.reeching.goodcountry.util.JsonCallback;
import com.reeching.goodcountry.util.LogUtil;
import com.reeching.goodcountry.util.PreferenceManager;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.ToastUtil;
import com.reeching.goodcountry.view.MineItem;
import com.reeching.goodcountry.view.TitleView;
import com.xw.repo.BubbleSeekBar;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.reeching.goodcountry.Constant.REQUEST_CODE_CHOOSE;

public class ReleaseArticleActivity extends BaseActivity {

    @BindView(R.id.title)
    TitleView mTitle;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.title_et)
    EditText mTitleEt;
    @BindView(R.id.content_tv)
    TextView mContentTv;
    @BindView(R.id.content_et)
    EditText mContentEt;
    @BindView(R.id.knowledge)
    RadioButton mKnowledge;
    @BindView(R.id.experience)
    RadioButton mExperience;
    @BindView(R.id.opinion)
    RadioButton mOpinion;
    @BindView(R.id.release_radio)
    RadioGroup mReleaseRadio;
    @BindView(R.id.view1)
    View mView1;
    @BindView(R.id.select_case)
    MineItem mSelectCase;
    @BindView(R.id.add_img)
    TextView mAddImg;
    @BindView(R.id.img_recycler)
    RecyclerView mImgRecycler;
    @BindView(R.id.release)
    Button mRelease;
    @BindView(R.id.issue_seek)
    BubbleSeekBar mIssueSeek;
    @BindView(R.id.coin_cus)
    EditText mCoinCus;
    @BindView(R.id.issue_tv)
    TextView mIssueTv;
    @BindView(R.id.other)
    TextView mOther;
    @BindView(R.id.progress)
    TextView mProgress;
    @BindView(R.id.zero)
    TextView mZero;
    private ArrayList<String> mPaths;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private TwoCategoryBean mTwoCategoryBean;
    private int mArticleType;
    private TwoCategoryBean.ObjectMultiArrayBean mMultiArrayBean;
    private int mWzType;

    @Override
    protected void initData() {
        PreferenceManager.getInstance().putInt("type", Constant.AUTHENTICATION_DOCTOR);
        int type = PreferenceManager.getInstance().getInt("type");
        switch (type) {
            case Constant.AUTHENTICATION_DOCTOR://医生
                mKnowledge.setVisibility(View.GONE);
                mReleaseRadio.check(R.id.opinion);
                break;
            case Constant.AUTHENTICATION_PATIENT://患者
                mOpinion.setVisibility(View.GONE);
                mReleaseRadio.check(R.id.knowledge);
                break;
        }
        getDiseaseCase();
    }

    private void getDiseaseCase() {
        OkGo.<TwoCategoryBean>get(Constant.BASE_URL + Constant.TwoCategory)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .execute(new JsonCallback<TwoCategoryBean>(TwoCategoryBean.class) {

                    @Override
                    public void onSuccess(Response<TwoCategoryBean> response) {
                        if (response.isSuccessful()) {
                            mTwoCategoryBean = response.body();
                        }
                    }
                });
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
        OptionsPickerView<String> picker1 = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

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
                    mPicker2 = new OptionsPickerBuilder(ReleaseArticleActivity.this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            mSelectCase.setDescription(level2.get(options1));
                            mMultiArrayBean = level_2.get(options1);
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
    protected void initViews() {
        Intent intent = getIntent();
        mArticleType = intent.getIntExtra("type", 1);
        if (mArticleType == 1) {
            mTitle.setTitle(getResources().getString(R.string.release_article));
            mIssueSeek.setVisibility(View.GONE);
            mIssueTv.setVisibility(View.GONE);
            mOther.setVisibility(View.GONE);
            mProgress.setVisibility(View.GONE);
            mZero.setVisibility(View.GONE);
        } else
            mTitle.setTitle(getResources().getString(R.string.release_issue));
        mTitle.setBackClick(this);
        mIssueSeek.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                if (progress == mIssueSeek.getMax()) {
                    mCoinCus.setVisibility(View.VISIBLE);
                    mProgress.setVisibility(View.GONE);
                } else {
                    mCoinCus.setVisibility(View.GONE);
                    mProgress.setVisibility(View.VISIBLE);
                    mProgress.setText(String.valueOf(progress));
                }
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

            }
        });
        mImgRecycler.setNestedScrollingEnabled(false);
        mImgRecycler.setHasFixedSize(true);
        mImgRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mPaths = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.img_item, mPaths) {

            @Override
            protected void convert(BaseViewHolder helper, final String item) {
                Glide.with(ReleaseArticleActivity.this)
                        .asBitmap().placeholder(R.mipmap.img_shoppingmall_default_jd).load(item).into((ImageView) helper.getView(R.id.imageView));
            }
        };
        View view = LayoutInflater.from(this).inflate(R.layout.img_item, null);
        ImageView img = view.findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(ReleaseArticleActivity.this)
                        .choose(MimeType.ofImage())//图片类型
                        .countable(true)//true:选中后显示数字;false:选中后显示对号
                        .maxSelectable(9)//可选的最大数
                        .capture(true)//选择照片时，是否显示拍照
                        .captureStrategy(new CaptureStrategy(true, "com.reeching.goodcountry.fileProvider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                        .imageEngine(new GlideEngine())//图片加载引擎
                        .forResult(REQUEST_CODE_CHOOSE);//
            }
        });
        img.setImageResource(R.mipmap.img_pl_tj);
        mAdapter.setFooterView(view);
        mAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                mPaths.remove(position);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
        mAdapter.setOnItemClickListener(new ImgAdapterClick(this, mPaths));
        mImgRecycler.addItemDecoration(new SpaceItemDecoration(10));
        mImgRecycler.setAdapter(mAdapter);
        mReleaseRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.knowledge:
                        mWzType = 1;
                        break;
                    case R.id.experience:
                        mWzType = 2;
                        break;
                    case R.id.opinion:
                        mWzType = 3;
                        break;
                }
            }
        });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_release_article;
    }

    @OnClick({R.id.select_case, R.id.release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_case:
                if (mTwoCategoryBean == null) {
                    ToastUtil.showToast(this, "加载类型中，请稍候。。。");
                } else {
                    setPicker();
                }
                break;
            case R.id.release:
                if (TextUtils.isEmpty(mTitleEt.getText())) {
                    ToastUtil.showToast(this, getString(R.string.input_title));
                } else if (TextUtils.isEmpty(mContentEt.getText())) {
                    ToastUtil.showToast(this, getString(R.string.input_content));
                } else if (mMultiArrayBean == null) {
                    ToastUtil.showToast(this, "请选择病例");
                } else
                    release();
                break;
        }
    }

    private void release() {
        PostRequest<ReleaseArticle> params = OkGo.<ReleaseArticle>post(Constant.BASE_URL + Constant.PUBLISHARTICLE)
                .params("openId", PreferenceManager.getInstance().getString("openId"))
                .params("jbTypeId", mMultiArrayBean.getParentId())//疾病分类二级分类
                .params("jbTagId", mMultiArrayBean.getId())//疾病分类三级分类
                .params("title", mTitleEt.getText().toString().trim())
                .params("content", mContentEt.getText().toString())
                .params("wzType", mWzType)//文章、问题类型
                .params("wzWt", mArticleType)//文章或问题（1：文章，2问题）
                .params("rewardMoney",
                        mCoinCus.getVisibility() == View.GONE ? mIssueSeek.getProgress() + "" : mCoinCus.getText().toString());// 问题悬赏金额
        for (String s :
                mPaths) {
            params.params("picPath" + mPaths.indexOf(s), new File(s));
        }
        params.execute(new JsonCallback<ReleaseArticle>(ReleaseArticle.class) {
            @Override
            public void onSuccess(Response<ReleaseArticle> response) {
                if (response.isSuccessful() && response.body().getResult() == 1) {
                    ToastUtil.showToast(ReleaseArticleActivity.this, "发表成功");
                    finish();
                } else
                    ToastUtil.showToast(ReleaseArticleActivity.this, "发表失败");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == Activity.RESULT_OK) {
            List<String> result = Matisse.obtainPathResult(data);
            mPaths.addAll(result);
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
