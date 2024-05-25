package com.example.hangil_app;

import static com.example.hangil_app.system.Hangil.BUILDING_ID;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hangil_app.data.BuildingBackground;
import com.example.hangil_app.data.BuildingInfo;
import com.example.hangil_app.info.InfoMapView;
import com.example.hangil_app.system.Hangil;
import com.example.hangil_app.system.ProgressSpin;

import java.util.ArrayList;
import java.util.List;

public class InfoIndoorActivity extends AppCompatActivity {
    private int buildingId;
    private InfoMapView infoMapView;
    private Button infoIndoorBackBtn;
    private LinearLayout floorBtnContainer;
    private List<Button> floorBtns = new ArrayList<>();
    private ProgressSpin progressSpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_indoor);

        //init
        floorBtnContainer = findViewById(R.id.floorBtnContainer);
        infoMapView = findViewById(R.id.infoMapView);
        infoIndoorBackBtn = findViewById(R.id.infoIndoorBackBtn);
        progressSpin = new ProgressSpin(this);
        buildingId = getIntent().getIntExtra(BUILDING_ID, 0);


        // 건물 내부 지도가 존재한다면
        if (BuildingBackground.getBitmaps()[buildingId].length > 1) {
            // 돌아가기 버튼 이벤트 생성
            infoIndoorBackBtn.setOnClickListener((event) -> {
                finish();
            });

            // 건물 정보 가져오기
            BuildingInfo buildingInfo = BuildingInfo.findBuildingInfo(buildingId);

            // 초기엔 1층으로 설정함
            Bitmap initBackground = BuildingBackground.getBitmaps()[buildingId][1];
            InfoMapView.setBackground(initBackground);

            // 카메라 설정
            InfoMapView.setOffsetX(800);
            InfoMapView.setOffsetY(2200);
            infoMapView.invalidate();

            for (int i = 1; i <= buildingInfo.floorCount; i++) {
                // 층 변경 버튼 생성
                Button floorBtn = new Button(this);
                floorBtn.setText(i+"F");
                floorBtn.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

                // 층 변경 버튼 이벤트 부착
                int floor = i;
                floorBtn.setOnClickListener((event) -> {
                    // 층에 맞게 배경 변경
                    Bitmap background = BuildingBackground.getBitmaps()[buildingId][floor];
                    InfoMapView.setBackground(background);

                    // 적용
                    infoMapView.invalidate();
                });

                floorBtnContainer.addView(floorBtn);
                floorBtns.add(floorBtn);
            }
        } else {
            // 만약 이미지가 준비 되지 않을 시
            Hangil.showToastShort(this, "준비되지 않은 건물 정보입니다.");
            finish();
        }
    }

    private void showProgressSpin() {
        progressSpin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressSpin.setCancelable(false);
        progressSpin.show();
    }

    private void hideProgressSpin() {
        progressSpin.dismiss();
    }


    @Override
    public void onStop() {
        super.onStop();
        finish();
    }
}