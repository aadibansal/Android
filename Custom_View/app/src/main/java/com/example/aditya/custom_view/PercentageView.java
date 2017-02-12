package com.example.aditya.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Created by Aditya on 2/6/2017.
 */

public class PercentageView extends FrameLayout {

    public PercentageView(Context context) {
        super(context);
    }

    public PercentageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet,
                R.styleable.PercentageView, 0, 0);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_percent_view, this);
        TextView mPercentageTextView = (TextView) view.findViewById(R.id.percentageTextView);
        try {
            float value = typedArray.getFloat(R.styleable.PercentageView_value, 0);
            @ColorRes int colorResource = typedArray.getResourceId(R.styleable.PercentageView_color_res, -1);
            float fontSize = typedArray.getDimension(R.styleable.PercentageView_fontSize, 0);

            mPercentageTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);

            NumberFormat numberFormat = NumberFormat.getPercentInstance();
            String percentage = numberFormat.format(value);
            mPercentageTextView.setText(percentage);

            if (colorResource != -1) {
                mPercentageTextView.setTextColor(ContextCompat.getColor(getContext(), colorResource));
            }
        } finally {
            typedArray.recycle();
        }
    }

}
