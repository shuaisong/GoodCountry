package com.reeching.goodcountry.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.bean.ShopCategoryBean;

import java.util.List;

/**
 * Created by lenovo on 2019/1/25.
 * auther:lenovo
 * Date：2019/1/25
 */
public class ShopAdapter extends BaseMultiItemQuickAdapter<ShopCategoryBean, BaseViewHolder> {
    public static final int TYPE_LEVEL_1 = 0;
    public static final int TYPE_LEVEL_2 = 1;
    private Context mContext;

    public ShopAdapter(Context context, List<ShopCategoryBean> data) {
        super(data);
        mContext = context;
        addItemType(TYPE_LEVEL_1, R.layout.community_second);
        addItemType(TYPE_LEVEL_2, R.layout.shop_third);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCategoryBean item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_1:
                helper.setText(R.id.second_name, item.getName());
                break;
            case TYPE_LEVEL_2:
                helper.setText(R.id.name, item.getName());
                helper.setText(R.id.price, "$" + item.getPrice() + "/天");
                helper.setText(R.id.price1, item.getPrice1() + "币");
                Glide.with(mContext).load(item.getPath()).error(R.mipmap.error).placeholder(R.mipmap.img_shoppingmall_default_jd).into((ImageView) helper.getView(R.id.image));
                break;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TYPE_LEVEL_2) {
                        return 1;
                    }
                    if (getItemViewType(position) == TYPE_LEVEL_1) {
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }
}
