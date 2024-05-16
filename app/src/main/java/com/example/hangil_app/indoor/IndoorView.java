package com.example.hangil_app.indoor;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class IndoorView extends View {
    public IndoorView(Context context) {
        super(context);
    }

    public IndoorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IndoorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float dpToPx(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (float) (dp * density);
    }

    // px를 dp로 변환
    public float pxToDp(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (float) (px / density);
    }

    @Override
    public void onDraw(Canvas canvas) {

    }
}
