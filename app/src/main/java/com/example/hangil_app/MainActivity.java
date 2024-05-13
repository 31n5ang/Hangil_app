package com.example.hangil_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hangil_app.tmap.DataManager;
import com.example.hangil_app.tmap.TMap;
import com.example.hangil_app.tmap.api.response.Building;
import com.skt.tmap.TMapPoint;

public class MainActivity extends AppCompatActivity {
    private TMap tMap;
    private DataManager dataManager;
    private FrameLayout container;
    private EditText destInput;
    private ImageView destInputGo;

    private ImageView currLocBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.tmapViewContainer);
        destInput = findViewById(R.id.destInput);
        destInputGo = findViewById(R.id.destInputGo);
        currLocBtn = findViewById(R.id.currLocBtn);

        tMap = TMap.getInstance(this, container, Hangil.APPKEY);
        dataManager = DataManager.getInstance(Hangil.API_URL);


        // 티맵이 준비 되면
        tMap.setOnMapReadyListener(() -> {
            tMap.setZoomLevel(17);

            // 처음으로 받아오는 위치로 티맵 화면 조정
            tMap.setOnLocationChangeFirstListener((firstPoint) -> {
                tMap.setCenter(firstPoint.getLatitude(), firstPoint.getLongitude(), true);
            });

            // 건물 정보 Rest API 불러온 후 마커 생성
            dataManager.requestGetBuildings((buildings) -> {
                for (Building building : buildings) {
                    int id = building.getId();
                    String name = building.getName();
                    String description = building.getDescription();
                    double latitude = building.getLatitude();
                    double longitude = building.getLongitude();
                    tMap.addMarker(id, null, new TMapPoint(latitude, longitude), name, description);
                }
            });

            // 내 위치로 티맵 화면을 조정하는 버튼
            currLocBtn.setOnClickListener((event) -> {
                tMap.setCenter(tMap.getLocationPoint().getLatitude(), tMap.getLocationPoint().getLongitude());
            });

            // 검색버튼을 누르게 되면
            destInputGo.setOnClickListener((event) -> {
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra(Hangil.SEARCH, destInput.getText().toString());
                startActivity(intent);
            });

            // TODO 이렇게 직접 SearchActivity의 멤버 인터페이스 메소드에 조작하는 것이 과연 올바를까..?
            // SearchActivity의 목록에서 '안내'버튼을 누르게 되면 호출
            SearchActivity.setOnStartGuideCallback((selectedSearchRoomData) -> {
                Hangil.suggestGuideDialog(
                        this,
                        String.format(
                            "%s안에 %s(으)로 안내를 시작 할까요?",
                            selectedSearchRoomData.getRoomName(),
                            selectedSearchRoomData.getRoomDetail()
                        ),
                        () -> {
                            // 긍정
                            Log.d(Hangil.TEST, "긍정");
                        },
                        () -> {
                            // 부정
                            Log.d(Hangil.TEST, "부정");
                        }
                );
            });

        });
    }
}

