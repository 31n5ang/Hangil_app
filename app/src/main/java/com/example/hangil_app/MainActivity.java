package com.example.hangil_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hangil_app.api.BuildingId;
import com.example.hangil_app.api.DataManager;
import com.example.hangil_app.api.NodeType;
import com.example.hangil_app.api.response.Building;
import com.example.hangil_app.system.Hangil;
import com.example.hangil_app.tmap.TMap;
import com.example.hangil_app.wifi.WifiHelper;
import com.skt.tmap.TMapPoint;

public class MainActivity extends AppCompatActivity {
    private TMap tMap;
    private DataManager dataManager;
    private FrameLayout container;
    private EditText destInput;
    private ImageView destInputGo;

    private ImageView currLocBtn;

    private WifiHelper wifiHelper;

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
        wifiHelper = new WifiHelper(this);

        // 티맵이 준비 되면
        tMap.setOnMapReadyListener(() -> {
            tMap.setZoomLevel(17);

            // 처음으로 받아오는 위치로 티맵 화면 조정
            tMap.setOnLocationChangeFirstListener((firstPoint) -> {
                tMap.setCenter(firstPoint.getLatitude(), firstPoint.getLongitude(), true);
            });

            // 건물 정보 Rest API 불러온 후 마커 생성
            dataManager.requestGetBuildings((buildings) -> {
                // 노드도 미리 받아오기
                dataManager.requestGetNodes(BuildingId.GONG2, NodeType.ROOM, nodes -> {});
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
                if (tMap.isGuideMode) {
                    // 안내 중이라면
                    suggestOffGuideDialog();
                } else {
                    // SearchActivity로 이동
                    Intent intent = new Intent(this, SearchActivity.class);
                    intent.putExtra(Hangil.SEARCH, destInput.getText().toString());
                    startActivity(intent);
                }
            });

            // 검색인풋을 누르게 되면
            destInput.setOnClickListener((event) -> {
                if (tMap.isGuideMode) {
                    // 안내 중이라면
                    suggestOffGuideDialog();
                } else {

                }
            });



            // SearchActivity의 목록에서 '안내'버튼을 누르게 되면 호출
            SearchActivity.setOnStartGuideCallback((selectedSearchRoomData) -> {
                Hangil.suggestGuideDialog(
                        this,
                        String.format(
                            "%s안에 %s 안내를 시작 할까요?",
                            selectedSearchRoomData.getRoomDetail(),
                            selectedSearchRoomData.getRoomName()
                        ),
                        () -> {
                            // 긍정
                            //TODO 경로 그리기
                            //TODO 공학2관 입구 마커 찍기
                            //TODO 입구 클릭 시 내부 지도로 전환하냐고 물음
                            //TODO 입구 노드 -> 도착 노드 GET
                            //TODO 내부 지도로 전환
                            //TODO 층마다 경로 정보 저장
                            //TODO 와이파이 스캔 -> 현재 위치에 따라 지도에 표시

                            // 검색창 비활성화 및 티맵 안내모드 활성화
                            setEnableDestInput(false);
                            destInput.setText(String.format("%s %s",
                                    selectedSearchRoomData.getRoomDetail(),
                                    selectedSearchRoomData.getRoomName()));
                            tMap.onGuideMode();
                        },
                        () -> {
                            // 부정
                        }
                );




            });
        });
    }

    private void setEnableDestInput(boolean isEnable) {
        if (isEnable) {
            destInput.setFocusableInTouchMode(true);
        } else {
            destInput.setFocusable(false);
        }
        destInput.setClickable(isEnable);
        destInput.setText("");
    }

    private void suggestOffGuideDialog() {
        Hangil.suggestGuideDialog(
                this,
                String.format("안내를 종료하고 목적지를 다시 설정할까요?"),
                () -> {
                    // 긍정
                    // 티맵 가이드모드 종료 및 검색창 활성화
                    tMap.offGuideMode();
                    setEnableDestInput(true);
                },
                () -> {
                    // 부정
                }
        );
    }
}

