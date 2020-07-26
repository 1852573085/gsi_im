package com.aqiang.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aqiang.common.R;

public class TitleBar extends RelativeLayout {
    private TextView left;
    private TextView title;
    private TextView right;
    private OnTitleBarViewClickListener onTitleBarViewClickListener;

    public void setOnTitleBarViewClickListener(OnTitleBarViewClickListener onTitleBarViewClickListener) {
        this.onTitleBarViewClickListener = onTitleBarViewClickListener;
    }

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_title, null);
        left = view.findViewById(R.id.tv_view_left);
        title = view.findViewById(R.id.tv_view_title);
        right = view.findViewById(R.id.tv_view_right);
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTitleBarViewClickListener.leftListener();
            }
        });

        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTitleBarViewClickListener.rightListener();
            }
        });
        addView(view);
        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        String leftText = typedArray.getString(R.styleable.TitleBar_leftText);
        if(leftText != null){
            left.setText(leftText);
        }
        int leftIcon = typedArray.getResourceId(R.styleable.TitleBar_leftIcon, 0);
        if(leftIcon != 0){
            Drawable drawable = ContextCompat.getDrawable(context, leftIcon);
            drawable.setBounds(0,0,50,50);
            left.setCompoundDrawables(drawable,null,null,null);
        }
        String txt = typedArray.getString(R.styleable.TitleBar_title);
        if(txt != null){
            title.setText(txt);
        }
        String rightText = typedArray.getString(R.styleable.TitleBar_rightText);
        if(rightText != null){
            right.setText(leftText);
        }
        int rightIcon = typedArray.getResourceId(R.styleable.TitleBar_rightIcon, 0);
        if(rightIcon != 0){
            Drawable drawable = ContextCompat.getDrawable(context, rightIcon);
            drawable.setBounds(0,0,50,50);
            right.setCompoundDrawables(drawable,null,null,null);
        }
        typedArray.recycle();
    }


    public void setTitleText(String text){
        title.setText(text);
    }
}
