package com.reeching.goodcountry.view;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.reeching.goodcountry.R;

/**
 * Created by lenovo on 2019/1/31.
 * auther:lenovo
 * Date：2019/1/31
 */
public class CustomLoadMoreView extends LoadMoreView {
    /**
     * load more layout
     *
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.quick_view_load_more;
    }

    /**
     * loading view
     *
     * @return
     */
    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    /**
     * load fail view
     *
     * @return
     */
    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    /**
     * load end view, you can return 0
     *
     * @return
     */
    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
