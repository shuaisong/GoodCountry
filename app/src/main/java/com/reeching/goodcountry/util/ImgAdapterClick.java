package com.reeching.goodcountry.util;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.previewlibrary.GPreviewActivity;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.ThumbViewInfo;
import com.reeching.goodcountry.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/3/5.
 * auther:lenovo
 * Date：2019/3/5
 */
public class ImgAdapterClick implements BaseQuickAdapter.OnItemClickListener {
    private List<String> picS;
    private Activity mContext;

    public ImgAdapterClick(Activity context, List<String> picS) {
        this.picS = picS;
        mContext = context;
    }

    private ArrayList<ThumbViewInfo> mThumbViewInfoList = new ArrayList<>();

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ThumbViewInfo info;
        mThumbViewInfoList.clear();
        for (String pic : picS) {
            Rect bounds = new Rect();
            //new ThumbViewInfo(图片地址);
            String url;
            if (!pic.startsWith("http")) {
                url = Constant.IMG_URL + pic;
            } else url = pic;
            info = new ThumbViewInfo(url);
            info.setBounds(bounds);
            mThumbViewInfoList.add(info);
        }
        GPreviewBuilder.from(mContext)
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
}
