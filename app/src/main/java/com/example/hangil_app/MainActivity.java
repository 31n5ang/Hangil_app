package com.example.hangil_app;

import static com.example.hangil_app.data.DataManager.BUILDING_COUNT;
import static com.example.hangil_app.data.DataManager.MIN_BUILDING_INDEX;
import static com.example.hangil_app.system.Hangil.BUILDING_ID;
import static com.example.hangil_app.system.Hangil.END_NODE;
import static com.example.hangil_app.system.Hangil.START_NODE;
import static com.example.hangil_app.tmap.TMap.ENTRANCE;
import static com.example.hangil_app.tmap.TMap.INIT_ZOOM_LEVEL;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hangil_app.data.BuildingInfo;
import com.example.hangil_app.data.DataManager;
import com.example.hangil_app.data.Entrance;
import com.example.hangil_app.data.NodeType;
import com.example.hangil_app.data.api.dto.Building;
import com.example.hangil_app.search.SearchRoomData;
import com.example.hangil_app.system.Hangil;
import com.example.hangil_app.tmap.TMap;
import com.skt.tmap.TMapPoint;
import com.skt.tmap.TMapView;
import com.skt.tmap.overlay.TMapMarkerItem;
import com.skt.tmap.poi.TMapPOIItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TMapView.OnClickListenerCallback {
    private TMap tMap;
    private DataManager dataManager;
    private FrameLayout container;
    private EditText destInput;
    private ImageView destInputGo;

    private ImageView currLocBtn;

    private int currGuideBuildingId = BuildingInfo.NONE.id;
    private int currGuideNodeId = -1;

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
            // 줌 레벨 설정
            tMap.setZoomLevel(INIT_ZOOM_LEVEL);

            // 처음으로 받아오는 위치로 티맵 화면 조정
            tMap.setOnLocationChangeFirstListener((firstPoint) -> {
                tMap.setCenter(firstPoint.getLatitude(), firstPoint.getLongitude(), true);
            });

            // 노드 정보 미리 받아오기
            for (int buildingId = MIN_BUILDING_INDEX; buildingId < (MIN_BUILDING_INDEX + BUILDING_COUNT); buildingId++) {
                dataManager.requestGetNodes(buildingId, NodeType.ROOM, nodes -> {});
            }

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
                    // 안내 중이라면 종료 안내
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
                            // 건물 이름
                            selectedSearchRoomData.getRoomDetail(),
                            // 호실
                            selectedSearchRoomData.getRoomName()
                        ),
                        () -> {
                            // 긍정
                            onGuideSetting(selectedSearchRoomData);
                        },
                        null
                );
            });

            // test
        });
    }
    private void addMarkerEntrance(int buildingId) {
        Bitmap entranceIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.entrance);
        for (BuildingInfo buildingInfo : BuildingInfo.values()) {
            // 해당 건물 찾기
            if (buildingInfo.id == buildingId) {
                // 입구 마커 생성
                int add = 0;
                for (Entrance entrance : buildingInfo.entrances) {
                    tMap.addMarker(ENTRANCE + add, entranceIcon,
                            entrance.getTMapPoint(), null, null);
                    ++add;
                }
                break;
            }
        }
    }

    private void removeMarkerEntrace(int buildingId) {
        for (BuildingInfo buildingInfo : BuildingInfo.values()) {
            // 해당 건물 찾기
            if (buildingInfo.id == buildingId) {
                int add = 0;
                for (Entrance entrance : buildingInfo.entrances) {
                    tMap.removeMarker(ENTRANCE + add);
                    add++;
                }
                break;
            }
        }
    }

    private void drawPathBuilding(int buildingId) {
        Building building = DataManager.buildingByIdMap.get(buildingId);
        TMapPoint dest = new TMapPoint(building.getLatitude(), building.getLongitude());
        tMap.findPath(tMap.getLocationPoint(), dest, (tMapPolyLine -> {
            tMap.drawPathPolyLine(tMapPolyLine);
        }));
    }

    private void erasePathBuilding() {
        tMap.erasePolyLine();
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
                    offGuideSetting();
                },
                null
        );
    }

    private void offGuideSetting() {
        // 티맵 가이드모드 종료 및 검색창 활성화
        tMap.offGuideMode();
        setEnableDestInput(true);

        // 티맵 입구 마커 삭제
        removeMarkerEntrace(currGuideBuildingId);

        // 경로 삭제
        erasePathBuilding();

        // 안내 중인 건물 없음으로 설정
        currGuideBuildingId = BuildingInfo.NONE.id;
        currGuideNodeId = -1;
    }

    private void onGuideSetting(SearchRoomData selectedSearchRoomData) {
        // 현재 안내중인 건물 설정
        currGuideBuildingId = selectedSearchRoomData.getBuildingId();
        currGuideNodeId = selectedSearchRoomData.getNodeId();

        // 검색창 비활성화 및 티맵 안내모드 활성화
        setEnableDestInput(false);

        // 검색창 바꾸기
        destInput.setText(String.format("%s %s",
                selectedSearchRoomData.getRoomDetail(),
                selectedSearchRoomData.getRoomName()));

        // 티맵 안내 모드 시작
        tMap.onGuideMode();

        // 토스트 메세지
        Hangil.showToastLong(this, "안내를 종료하려면 검색창을 터치하세요!");

        // 입구 마커 생성
        addMarkerEntrance(currGuideBuildingId);

        // 경로 생성
        drawPathBuilding(currGuideBuildingId);
    }

    @Override
    public void onPressDown(ArrayList<TMapMarkerItem> tMapMarkerItems, ArrayList<TMapPOIItem> poiItems,
                            TMapPoint tMapPoint, PointF pointF) {
        if (tMapMarkerItems.isEmpty()) return;
        else {
            TMapMarkerItem selectedTmapMarkerItem = tMapMarkerItems.get(0);
            if (tMap.isEntraceMarker(selectedTmapMarkerItem)) {
                // 입구 마커라면
                int add = 0;
                // 터치한 입구 마커
                int selectedMarkerId = Integer.parseInt(selectedTmapMarkerItem.getId());
                for (BuildingInfo buildingInfo : BuildingInfo.values()) {
                    for (Entrance entrance : buildingInfo.entrances) {
                        // 터치한 입구 마커 비교
                        int markerId = ENTRANCE + add;
                        if (markerId == selectedMarkerId)
                            // 내부 지도 안내로 전환 제안
                            Hangil.suggestGuideDialog(
                                    this,
                                    "선택한 입구를 출발지로 실내 지도로 전환할까요?",
                                    () -> {
                                        // 긍정
                                        // 현재 안내 종료
                                        Intent intent = new Intent(this, IndoorActivity.class);

                                        // 실내 지도 Activity에 건물 번호, 출발 노드, 도착 노드 전달
                                        intent.putExtra(BUILDING_ID, currGuideBuildingId);
                                        intent.putExtra(START_NODE, entrance.getNodeId());
                                        intent.putExtra(END_NODE, currGuideNodeId);
                                        startActivity(intent);

                                        // 안내 종료
                                        offGuideSetting();
                                    },
                                    null
                            );
                        ++add;
                    }
                }
            }
        }
    }

    @Override
    public void onPressUp(ArrayList<TMapMarkerItem> tMapMarkerItems, ArrayList<TMapPOIItem> poiItems,
                          TMapPoint tMapPoint, PointF pointF) {

    }
}

