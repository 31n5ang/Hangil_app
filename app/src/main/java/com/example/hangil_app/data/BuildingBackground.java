package com.example.hangil_app.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.hangil_app.R;

import lombok.Setter;

public class BuildingBackground {
    private static Bitmap[][] bitmaps;
    @Setter
    private static Context context;

    public static Bitmap[][] getBitmaps() {
        if (bitmaps != null) return bitmaps;
        return bitmaps = new Bitmap[][]{
                {}, // NONE
                {
                        // 공학 2관
                        null,
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.gong2_1_120),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.gong2_2_120),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.gong2_3_120),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.gong2_4_120)
                },
        };
    }
}
