package com.reeching.goodcountry.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.reeching.goodcountry.R;
import com.reeching.goodcountry.bean.Communitybean;

import java.util.List;

/**
 * Created by lenovo on 2019/1/25.
 * auther:lenovo
 * Date：2019/1/25
 */
public class CommunityAdapter extends BaseMultiItemQuickAdapter<Communitybean, BaseViewHolder> {
    public static final int TYPE_LEVEL_1 = 0;
    public static final int TYPE_LEVEL_2 = 1;

    public CommunityAdapter(List<Communitybean> data) {
        super(data);
        addItemType(TYPE_LEVEL_1, R.layout.community_second);
        addItemType(TYPE_LEVEL_2, R.layout.community_third);
    }

    @Override
    public int getItemViewType(int position) {
        int defItemViewType = getDefItemViewType(position);
        if (defItemViewType == TYPE_LEVEL_2) {
            return TYPE_LEVEL_2;
        }
        return super.getItemViewType(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, Communitybean item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_1:
                helper.setNestView(R.id.second_name);
                helper.setText(R.id.second_name, item.getName());
                break;
            case TYPE_LEVEL_2:
                helper.setNestView(R.id.third_name);
                helper.setText(R.id.third_name, item.getName());
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
