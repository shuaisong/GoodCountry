package com.reeching.goodcountry.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reeching.goodcountry.Constant;
import com.reeching.goodcountry.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by lenovo on 2019/1/23.
 * auther:lenovo
 * Date：2019/1/23
 */
public class Imgloader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RequestOptions options = new RequestOptions().fitCenter();
        if (path instanceof Integer)
            Glide.with(context).load((Integer) path).centerCrop().error(R.mipmap.error).placeholder(R.mipmap.img_shoppingmall_default_jd).apply(options).into(imageView);
        if (path instanceof String) {
            if (!((String) path).startsWith("http")) {
                path = Constant.IMG_URL + path;
            }
            Glide.with(context).load(path).apply(options).centerCrop().error(R.mipmap.error).placeholder(R.mipmap.img_shoppingmall_default_jd).into(imageView);
        }
    }

}
