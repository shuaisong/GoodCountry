package com.reeching.goodcountry.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.reeching.goodcountry.R;


/**
 * Created by lenovo on 2019/1/28.
 * auther:lenovo
 * Date：2019/1/28
 */
public class MineItem extends ConstraintLayout {

    private TextView mDescription;

    public MineItem(Context context) {
        this(context, null);
    }

    public MineItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MineItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.fg_mine_item, this, true);
        TextView title = view.findViewById(R.id.title);
        mDescription = view.findViewById(R.id.description);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MineItem);
        if (typedArray != null) {
            title.setText(typedArray.getString(R.styleable.MineItem_title));
            int drawable = typedArray.getResourceId(R.styleable.MineItem_drawStart, -1);
            if (drawable != -1) {
                float dimension = typedArray.getDimension(R.styleable.MineItem_img_padding_end, 0);
                title.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);
                title.setCompoundDrawablePadding((int) dimension);
            }
            mDescription.setText(typedArray.getString(R.styleable.MineItem_description));
            typedArray.recycle();
        }
    }

    public void setDescription(String description) {
        mDescription.setText(description);
    }
}
