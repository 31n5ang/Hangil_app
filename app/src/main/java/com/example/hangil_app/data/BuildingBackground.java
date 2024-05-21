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
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.gong2),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.gong2_1),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.gong2_2),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.gong2_3),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.gong2_4)
                },
                {
                        // 공학 1관 - 2
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.gong1)
                },
                {
                        // 공학 3관 - 3
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.gong3)
                },
                {
                        // 공학 4관 - 4
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.gong4)
                },
                {
                        // 담헌실학관 - 5
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.damheon),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.damheon_1),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.damheon_2),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.damheon_3),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.damheon_4),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.damheon_5),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.damheon_6),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.damheon_7),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.damheon_8),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.damheon_9),
                },
                {
                        // 국제교육센터(GEC) - 6
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.gec)
                },
                {
                        // 대학본부(본관) - 7
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.bonbu),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.bonbu_1),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.bonbu_2),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.bonbu_3),
                },
                {
                        // 인문경영관 - 8
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.inmun),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.inmun_1),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.inmun_2),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.inmun_3),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.inmun_4),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.inmun_5),
                },
                {
                        // 다담 미래학습관 - 9
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.dadam)
                },
                {
                        // 다산정보관 - 10
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.dasan),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.dasan_1),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.dasan_2),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.dasan_3),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.dasan_4),
                },
                {
                        // 학생회관 - 11
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.haksaeng),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.haksaeng_1),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.haksaeng_2),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.haksaeng_3),
                },
                {
                        // 학생통합지원센터 - 12
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.haksaeng)
                },
                {
                        // 복지관 - 13
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.bokji),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.bokji_1),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.bokji_2),
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(),
                                R.drawable.bokji_3),
                },
                {
                        // 중앙공원 - 14
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.jungdang)
                },
                {},
                {
                        // 새롬관 (교육원실습동) - 16
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.saerom)
                },
                {
                        // 산학협력관 - 17
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.sanhak)
                },
                {
                        // 강당 - 18
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.gangdang)
                },
                {
                        // 운동장 - 19
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.un_dongjang)
                },
                {
                        // 체육관 - 20
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.cheyuk)
                },
                {
                        // R&D돔(나래돔) - 21
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.rnd_dom)
                },
                {
                        // 캠퍼스 컴퍼니 - 22
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.campus_co)
                },
                {
                        // 스마트러닝팩토리 - 23
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.smart)
                },
                {
                        // 창업보육관 - 24
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.changup)
                },
                {
                        // 테니스장 부대시설 - 25
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.tennis)
                },
                {
                        // 나우리인성관 - 26
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.naouri)
                },
                {
                        // 공동직장어린이집 - 27
                        BitmapFactory.decodeResource(BuildingBackground.context.getResources(), R.drawable.eoryeonjib)
                },
                {
                        // 101동(해울관) - 28
                },
                {
                        // 102동(예지관) - 29
                },
                {
                        // 103동(예솔관) - 30
                },
                {
                        // 104동(다솔관) - 31
                },
                {
                        // 105동(함지관) - 32
                },
                {
                        // 106동(한울관) - 33
                },
                {
                        // 201동(솔빛관) - 34
                },
                {
                        // 202동(청솔관) - 35
                },
                {
                        // 203동(IH) - 36
                },
                {
                        // 204동(은솔관) - 37
                },
                {
                        // 205동(참빛관) - 38
                }
        };
    }
}
