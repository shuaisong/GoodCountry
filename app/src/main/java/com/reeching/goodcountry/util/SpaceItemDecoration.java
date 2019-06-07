package com.reeching.goodcountry.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by lenovo on 2018/4/15.
 * auther:lenovo
 * Date：2018/4/15
 */

public class SpaceItemDecoration extends ItemDecoration {
    private int mSpace;
    private Paint dividerPaint;

    public SpaceItemDecoration(float mSpace) {
        this.mSpace = (int) mSpace;
    }

    public SpaceItemDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    public SpaceItemDecoration(int mSpace, int color) {

        this.mSpace = mSpace;
        this.dividerPaint = new Paint();
        dividerPaint.setColor(color);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int mPosition = parent.getChildAdapterPosition(view);
        RecyclerView.LayoutManager mLayoutManager = parent.getLayoutManager();
        if (mLayoutManager instanceof GridLayoutManager) {
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.bottom = mSpace;
            int mSpanCount = ((GridLayoutManager) mLayoutManager).getSpanCount();
            if (mSpanCount == 1)
                if (mPosition == 0) {
                    outRect.top = mSpace;
                }
            if (mSpanCount >= 2) {
                if (mPosition == 0 | mPosition == 1) {
                    outRect.top = mSpace;
                }
            }
        }
        if (mLayoutManager instanceof LinearLayoutManager) {
            outRect.bottom = mSpace;
            outRect.top = mSpace;
        }
        if (mLayoutManager instanceof StaggeredGridLayoutManager) {

            int mSpanCount = ((StaggeredGridLayoutManager) mLayoutManager).getSpanCount();
            int column = mPosition % mSpanCount; // item column
            outRect.left = mSpace - column * mSpace / mSpanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * mSpace / mSpanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (mPosition < mSpanCount) { // top edge
                outRect.top = mSpace;
            }
            outRect.bottom = mSpace; // item bottom
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (dividerPaint == null)
            super.onDraw(c, parent, state);
        else {
            int childCount = parent.getChildCount();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            for (int i = 0; i < childCount - 1; i++) {
                View view = parent.getChildAt(i);
                float top = view.getBottom();
                float bottom = view.getBottom() + mSpace;
                c.drawRect(left, top, right, bottom, dividerPaint);
            }
        }
    }
}
