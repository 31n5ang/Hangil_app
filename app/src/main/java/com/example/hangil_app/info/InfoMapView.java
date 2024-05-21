package com.example.hangil_app.info;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

import lombok.Setter;

public class InfoMapView extends View {
    @Setter
    private static Bitmap background;
    private GestureDetector gestureDetector;
    private Scroller scroller;
    @Setter
    public static float offsetX = 0;
    @Setter
    public static float offsetY = 0;

    public InfoMapView(Context context) {
        super(context);
        init(context);
    }

    public InfoMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InfoMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        gestureDetector = new GestureDetector(context,
                new com.example.hangil_app.info.InfoMapView.GestureListener());
        scroller = new Scroller(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(-offsetX, -offsetY);
        drawContent(canvas);
        canvas.restore();
    }

    private void drawContent(Canvas canvas) {
        if (background != null) {
            canvas.drawBitmap(background, 0, 0, null);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (background != null && scroller.computeScrollOffset()) {
            offsetX = scroller.getCurrX();
            offsetY = scroller.getCurrY();
            invalidate();
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (background != null) {
                offsetX += distanceX;
                offsetY += distanceY;

                // Bound checking
                offsetX = Math.max(0, Math.min(offsetX, background.getWidth() - (float) getWidth() / 2));
                offsetY = Math.max(0, Math.min(offsetY, background.getHeight() - (float) getHeight() / 2));
            }

            invalidate();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (background != null) {
                scroller.fling((int) offsetX, (int) offsetY, (int) -velocityX, (int) -velocityY,
                        0, background.getWidth() - getWidth(), 0, background.getHeight() - getHeight());
            }
            return true;
        }
    }
}
