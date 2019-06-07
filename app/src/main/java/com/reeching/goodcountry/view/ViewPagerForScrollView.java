package com.reeching.goodcountry.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.reeching.goodcountry.R;
import com.reeching.goodcountry.util.LogUtil;

/**
 * scrollView嵌套viewpager
 * viewpager高度自适应
 */

public class ViewPagerForScrollView extends ViewPager {

    public ViewPagerForScrollView(Context context) {
        super(context);
    }

    public ViewPagerForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    int height = 0;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) height = h;
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public void resetHeight(int index) {

       /* View childAt = getChildAt(index);
        LogUtil.d(childAt.toString());
        int height = childAt.findViewById(R.id.disease_recycler).getHeight();
        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, height);
        } else {
            layoutParams.height = height;
        }
        childAt.measure(0, 0);
        childAt.setLayoutParams(layoutParams);
        childAt.requestLayout();
        LogUtil.d(height);
        LogUtil.d(getLayoutParams().toString());*/
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) getLayoutParams();
        if (params == null) {
            params = new  ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.MATCH_PARENT, height);
        } else {
            params.height = height;
        }
//        measure(0, 0);
        setLayoutParams(params);
//        requestLayout();
    }

}