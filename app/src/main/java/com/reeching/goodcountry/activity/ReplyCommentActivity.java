package com.reeching.goodcountry.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.previewlibrary.GPreviewActivity;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.ZoomMediaLoader;
import com.previewlibrary.enitity.ThumbViewInfo;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.util.GlideEngine;
import com.reeching.goodcountry.util.ImageLoader;
import com.reeching.goodcountry.util.SpaceItemDecoration;
import com.reeching.goodcountry.util.StatusBarUtil;
import com.reeching.goodcountry.view.MineItem;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.reeching.goodcountry.Constant.REQUEST_CODE_CHOOSE;

public class ReplyCommentActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.reply_et)
    EditText mReplyEt;
    @BindView(R.id.img_recycler)
    RecyclerView mImgRecycler;
    @BindView(R.id.select_article)
    MineItem mSelectArticle;
    @BindView(R.id.reply_bt)
    Button mReplyBt;
    private ArrayList<String> mPaths;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_comment);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        ButterKnife.bind(this);
        int type = getIntent().getIntExtra("type", 1);
        if (type == Constant.RELEASE_COMMENT) {
            mTitle.setText("发表评论");
            mReplyBt.setText("发表评论");
        } else {
            mTitle.setText("回复评论");
            mReplyBt.setText("回复评论");
        }
        initView();
    }

    ArrayList<ThumbViewInfo> mThumbViewInfoList = new ArrayList<>();

    private void initView() {
        mImgRecycler.setNestedScrollingEnabled(false);
        mImgRecycler.setHasFixedSize(true);
        mImgRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mPaths = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.img_item, mPaths) {

            @Override
            protected void convert(BaseViewHolder helper, final String item) {
                Glide.with(ReplyCommentActivity.this).asBitmap().load(item).into((ImageView) helper.getView(R.id.imageView));
                helper.setNestView(R.id.imageView);
            }
        };
        View view = LayoutInflater.from(this).inflate(R.layout.img_item, null);
        ImageView img = view.findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(ReplyCommentActivity.this)
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
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ThumbViewInfo info;
                mThumbViewInfoList.clear();
                for (int i = 0; i < mPaths.size(); i++) {
                    Rect bounds = new Rect();
                    //new ThumbViewInfo(图片地址);
                    info = new ThumbViewInfo(mPaths.get(i));
                    info.setBounds(bounds);
                    mThumbViewInfoList.add(info);
                }
                GPreviewBuilder.from(ReplyCommentActivity.this)
                        //是否使用自定义预览界面，当然8.0之后因为配置问题，必须要使用
                        .to(GPreviewActivity.class)
                        .setData(mThumbViewInfoList)
                        .setCurrentIndex(position)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        // 小圆点
                        //  .setType(GPreviewBuilder.IndicatorType.Dot)
                        .start();//启动

            }
        });
        mImgRecycler.addItemDecoration(new SpaceItemDecoration(10));
        mImgRecycler.setAdapter(mAdapter);
    }

    @OnClick({R.id.back, R.id.reply_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.reply_bt:
                finish();
                break;
        }
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
}
