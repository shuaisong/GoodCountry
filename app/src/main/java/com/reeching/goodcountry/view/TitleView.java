package com.reeching.goodcountry.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.reeching.goodcountry.R;

/**
 * Created by lenovo on 2019/1/31.
 * auther:lenovo
 * Date：2019/1/31
 */
public class TitleView extends ConstraintLayout {

    private TextView mTitle;
    private ImageView mBack;

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.title_view, this, true);
        mTitle = view.findViewById(R.id._title);
        mBack = view.findViewById(R.id.back);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        if (typedArray != null) {
            mTitle.setText(typedArray.getString(R.styleable.TitleView_title_title));
            typedArray.recycle();
        }
    }

    public void setTitle(String s) {
        mTitle.setText(s);
    }

    public void setBackClick(final Activity activity) {
        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
}
