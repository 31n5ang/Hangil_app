package com.example.hangil_app.indoor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hangil_app.R;

public class IndoorMapView extends View {
    private Bitmap building1F;
    private float offsetX = 0f; // X축 오프셋
    private float offsetY = 0f; // Y축 오프셋
    private float lastTouchX; // 마지막 터치 위치
    private float lastTouchY;


    // dp를 px로 변환
    public float dpToPx(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (float) (dp * density);
    }

    // px를 dp로 변환
    public float pxToDp(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (float) (px / density);
    }


    public IndoorMapView(Context context) {
        super(context);
        init();
    }

    public IndoorMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndoorMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        building1F = BitmapFactory.decodeResource(getResources(), R.drawable.gong2_3);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(offsetX, offsetY);
        canvas.drawBitmap(building1F, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(30);
        canvas.drawPoint(dpToPx(getContext(), 150f), dpToPx(getContext(), 150f), paint);
        canvas.translate(offsetX, offsetY);
        canvas.translate(offsetX, offsetY);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouchX = event.getX(); // 터치 시작 위치
                lastTouchY = event.getY();
                return true; // 이벤트 처리 완료

            case MotionEvent.ACTION_MOVE:
                float currentX = event.getX();
                float currentY = event.getY();

                float dx = currentX - lastTouchX; // x축 이동량
                float dy = currentY - lastTouchY; // y축 이동량

                offsetX += dx; // 오프셋 업데이트
                offsetY += dy;

                lastTouchX = currentX; // 터치 위치 업데이트
                lastTouchY = currentY;

                invalidate(); // 뷰 다시 그리기 요청
                return true; // 이벤트 처리 완료

            default:
                return super.onTouchEvent(event); // 기본 처리
        }
    }

}

