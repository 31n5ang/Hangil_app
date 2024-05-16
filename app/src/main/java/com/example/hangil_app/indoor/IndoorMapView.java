package com.example.hangil_app.indoor;

import static com.example.hangil_app.system.Hangil.TEST;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

import com.example.hangil_app.R;

public class IndoorMapView extends View {
    public static Bitmap background;
    private Bitmap me;
    private GestureDetector gestureDetector;
    private Scroller scroller;
    private float offsetX = 0;
    private float offsetY = 0;
    private Paint paint;
    public static Coord meCoord;

    public IndoorMapView(Context context) {
        super(context);
        init(context);
    }

    public IndoorMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IndoorMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        background = BitmapFactory.decodeResource(getResources(), R.drawable.gong2_1_120);
        me = BitmapFactory.decodeResource(getResources(), R.drawable.me);
        gestureDetector = new GestureDetector(context, new GestureListener());
        scroller = new Scroller(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10f);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (background != null) {
            canvas.save();
            canvas.translate(-offsetX, -offsetY);
            drawContent(canvas);
            canvas.restore();
        }
    }

    private void drawContent(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(me, meCoord.getX() - (float) me.getWidth() / 2,
                meCoord.getY() - (float) me.getHeight() / 2, null);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TEST, String.format("%d, %d", (int)(event.getX() + offsetX),
                    (int)(event.getY() + offsetY)));
        }
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.getCurrX();
            offsetY = scroller.getCurrY();
            invalidate();
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            offsetX += distanceX;
            offsetY += distanceY;

            // Bound checking
            offsetX = Math.max(0, Math.min(offsetX, background.getWidth() - getWidth()));
            offsetY = Math.max(0, Math.min(offsetY, background.getHeight() - getHeight()));

            invalidate();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            scroller.fling((int) offsetX, (int) offsetY, (int) -velocityX, (int) -velocityY,
                    0, background.getWidth() - getWidth(), 0, background.getHeight() - getHeight());
            return true;
        }
    }

    public void changeBackground(Bitmap background) {
        this.background = background;
        offsetX = 0;
        offsetY = 0;
        invalidate();
    }
}