package com.example.hangil_app;

import static com.example.hangil_app.system.Hangil.API_URL;
import static com.example.hangil_app.system.Hangil.BUILDING_ID;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hangil_app.data.BuildingBackground;
import com.example.hangil_app.data.DataManager;
import com.example.hangil_app.data.api.dto.Building;

public class InfoActivity extends AppCompatActivity {
    private TextView infoBuildingName;
    private TextView infoBuildingDescription;
    private ImageView infoBackBtn;
    private Button infoIndoorBtn;
    private ImageView infoBuildingImg;
    private int buildingId;
    private final DataManager dataManager = DataManager.getInstance(API_URL);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);



        // initialize
        infoBuildingName = findViewById(R.id.infoBuildingName);
        infoBuildingDescription = findViewById(R.id.infoBuildingDescription);
        infoBackBtn = findViewById(R.id.infoBackBtn);
        infoIndoorBtn = findViewById(R.id.infoIndoorBtn);
        infoBuildingImg = findViewById(R.id.infoBuildingImg);

        buildingId = getIntent().getIntExtra(BUILDING_ID, 1);
        BuildingBackground.setContext(this);

        // 건물 이미지 설정
        setInfoBuildingImg(buildingId);

        // 건물 정보 요청
        dataManager.requestGetBuildings((buildings) -> {
            for (Building building : buildings) {
                if (building.getId() == buildingId) {
                    // 설명 제공
                    String name = building.getName();
                    String description = building.getDescription();

                    // 건물 설명 설정
                    setInfoBuildingTexts(name, description);
                }
            }
        });

        // 내부 보기 버튼 클릭 시
        infoIndoorBtn.setOnClickListener((event) -> {
            Intent intent = new Intent(this, InfoIndoorActivity.class);
            intent.putExtra(BUILDING_ID, buildingId);
            startActivity(intent);
        });

        // 돌아가기 버튼 클릭 시
        infoBackBtn.setOnClickListener((event) -> {
            finish();
        });
    }

    private void setInfoBuildingImg(int buildingId) {
        // 건물 이미지 삽입
        int size = BuildingBackground.getBitmaps()[buildingId].length;
        if (size > 0) {
            Bitmap img = BuildingBackground.getBitmaps()[buildingId][0];
            if (img != null) {
                // 이미지 설정
                infoBuildingImg.setImageBitmap(img);

                // MainActivity에 건물 정보 불러왔다고 알림
                MainActivity.getOnInfoBuildingReadyListener().onInfoBuildingReady();
            }
        }
    }

    private void setInfoBuildingTexts(String name, String description) {
        infoBuildingName.setText(name);
        infoBuildingDescription.setText(description);
    }
}