package com.reeching.goodcountry.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reeching.goodcountry.R;

/**
 * Created by lenovo on 2019/1/29.
 * auther:lenovo
 * Date：2019/1/29
 */
public class TextEditView extends ConstraintLayout {

    private EditText mContent;

    public TextEditView(Context context) {
        this(context, null);
    }

    public TextEditView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.text_edit_view, this, true);
        TextView title = view.findViewById(R.id.title);
        mContent = view.findViewById(R.id.content);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextEditView);
        if (typedArray != null) {
            title.setText(typedArray.getString(R.styleable.TextEditView_text_title));
            mContent.setHint(typedArray.getString(R.styleable.TextEditView_edit_hint));
            mContent.setText(typedArray.getString(R.styleable.TextEditView_edit_content));
            typedArray.recycle();
        }
    }

    public CharSequence getText() {
        if (mContent != null)
            return mContent.getText();
        else return "";
    }
}
