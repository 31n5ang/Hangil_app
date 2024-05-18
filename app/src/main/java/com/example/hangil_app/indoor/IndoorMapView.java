package com.example.hangil_app.indoor;

import static com.example.hangil_app.system.Hangil.TEST;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

import com.example.hangil_app.R;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class IndoorMapView extends View {
    @Setter
    private static Bitmap background;
    private static Bitmap endMarker;
    private static Bitmap me;
    private GestureDetector gestureDetector;
    private Scroller scroller;
    @Setter
    public static float offsetX = 0;
    @Setter
    public static float offsetY = 0;
    private Paint paint;
    @Getter
    @Setter
    private static Coord meCoord;
    @Getter
    @Setter
    private static Coord endMarkerCoord;
    @Getter
    @Setter
    private static List<Coord> path = new ArrayList<>();

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
        me = BitmapFactory.decodeResource(getResources(), R.drawable.me);
        endMarker = BitmapFactory.decodeResource(getResources(), R.drawable.blue_marker);

        gestureDetector = new GestureDetector(context, new GestureListener());

        scroller = new Scroller(context);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
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
        if (!path.isEmpty()) {
            Path indoorPath = new Path();
            indoorPath.moveTo(path.get(0).getX(), path.get(0).getY());
            for (Coord coord : path) {
                indoorPath.lineTo(coord.getX(), coord.getY());
            }
            canvas.drawPath(indoorPath, paint);
        }
        if (meCoord != null ) canvas.drawBitmap(me, meCoord.getX() - (float) me.getWidth() / 2,
                meCoord.getY() - (float) me.getHeight() / 2, null);
        if (endMarkerCoord != null) {
            // 노드 위에 찍기
            canvas.drawBitmap(endMarker, endMarkerCoord.getX() - (float) endMarker.getWidth() / 2,
                    endMarkerCoord.getY() - (float) endMarker.getHeight(), null);
        }

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