package com.example.hangil_app;

import static com.example.hangil_app.system.Hangil.BUILDING_ID;
import static com.example.hangil_app.system.Hangil.END_NODE_FLOOR;
import static com.example.hangil_app.system.Hangil.END_NODE_ID;
import static com.example.hangil_app.system.Hangil.END_NODE_NAME;
import static com.example.hangil_app.system.Hangil.END_NODE_NUMBER;
import static com.example.hangil_app.system.Hangil.GUIDE_TYPE;
import static com.example.hangil_app.system.Hangil.OUTDOOR_GUIDE;
import static com.example.hangil_app.system.Hangil.SEARCH_TYPE;
import static com.example.hangil_app.system.Hangil.SEARCH_TYPE_END;
import static com.example.hangil_app.system.Hangil.SEARCH_TYPE_START;
import static com.example.hangil_app.system.Hangil.START_NODE_FLOOR;
import static com.example.hangil_app.system.Hangil.START_NODE_ID;
import static com.example.hangil_app.system.Hangil.START_NODE_NUMBER;
import static com.example.hangil_app.system.Hangil.suggestGuideDialog;
import static com.example.hangil_app.tmap.TMap.ENTRANCE;
import static com.example.hangil_app.tmap.TMap.INIT_ZOOM_LEVEL;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hangil_app.data.BuildingInfo;
import com.example.hangil_app.data.DataManager;
import com.example.hangil_app.data.Entrance;
import com.example.hangil_app.data.api.dto.Building;
import com.example.hangil_app.data.api.dto.Node;
import com.example.hangil_app.search.OnSelectRoomCallback;
import com.example.hangil_app.search.SearchRoomData;
import com.example.hangil_app.system.Hangil;
import com.example.hangil_app.system.ProgressSpin;
import com.example.hangil_app.tmap.TMap;
import com.skt.tmap.TMapPoint;
import com.skt.tmap.TMapView;
import com.skt.tmap.overlay.TMapMarkerItem;
import com.skt.tmap.poi.TMapPOIItem;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class MainActivity extends AppCompatActivity implements TMapView.OnClickListenerCallback {
    private TMap tMap;
    private DataManager dataManager;
    private FrameLayout container;
    private TextView endInput;
    private TextView startInput;
    private ImageView currLocBtn;
    private ImageView startGuideBtn;
    private ProgressSpin progressSpin;
    @Getter
    @Setter
    private static boolean isStartIsOutdoor = false;
    private SearchRoomData startRoomData = null;
    private SearchRoomData endRoomData = null;
    @Getter
    private static OnSelectRoomCallback onOutdoorSelectRoomCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.tmapViewContainer);
        startInput = findViewById(R.id.startInput);
        startGuideBtn = findViewById(R.id.startGuideBtn);
        endInput = findViewById(R.id.endInput);
        currLocBtn = findViewById(R.id.currLocBtn);
        progressSpin = new ProgressSpin(this);

        tMap = TMap.getInstance(this, container, Hangil.APPKEY);
        dataManager = DataManager.getInstance(Hangil.API_URL);

        // 로딩 창 띄우기
        showProgressSpin();

        // 처음으로 받아오는 위치로 티맵 화면 조정
        tMap.setOnLocationChangeFirstListener((firstPoint) -> {
            // 위치 조정
            tMap.setCenter(firstPoint.getLatitude(), firstPoint.getLongitude(), true);

            // 로딩 창 없애기
            hideProgressSpin();
        });

        // 티맵 지도가 준비 되면
        tMap.setOnMapReadyListener(() -> {
            tMap.isTmapReady = true;

            // 기본 위치 세팅 (학교 좌표)
            tMap.setCenter(36.764035, 127.281957);

            // 기본 줌 레벨 설정
            tMap.setZoomLevel(INIT_ZOOM_LEVEL);

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

            // 출발지 텍스트뷰를 누르게 되면
            startInput.setOnClickListener((event) -> {
                if (!tMap.isLocationChanged) return;
                if (tMap.isGuideMode) {
                    // 안내 중이라면 종료 안내
                    suggestOffGuideDialog();
                } else {
                    // 현재 위치로 출발지를 설정할건지 물어보기
                    suggestGuideDialog(
                            this,
                            "출발지를 '현재 위치'로 선택하려고 하셨나요?",
                            () -> {
                                // 긍정
                                isStartIsOutdoor = true;
                                startRoomData = null;
                                startInput.setText("현재 사용자의 위치");
                            },
                            () -> {
                                // 부정
                                // SearchActivity로 이동
                                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                                intent.putExtra(SEARCH_TYPE, SEARCH_TYPE_START);
                                intent.putExtra(GUIDE_TYPE, OUTDOOR_GUIDE);
                                startActivity(intent);
                            }
                    );
                }
            });

            // 도착지 텍스트뷰를 누르게 되면
            endInput.setOnClickListener((event) -> {
                if (!tMap.isLocationChanged) return;
                if (tMap.isGuideMode) {
                    // 안내 중이라면 종료 안내
                    suggestOffGuideDialog();
                } else {
                    // SearchActivity로 이동
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    intent.putExtra(SEARCH_TYPE, SEARCH_TYPE_END);
                    intent.putExtra(GUIDE_TYPE, OUTDOOR_GUIDE);
                    startActivity(intent);
                }
            });

            // 안내 버튼을 누르게 되면
            startGuideBtn.setOnClickListener((event) -> {
                if (tMap.isGuideMode) {
                    // 이미 안내 중이라면 무시
                    return;
                }

                if ((startRoomData == null && !isStartIsOutdoor) || endRoomData == null) {
                    // 출발지와 도착지 입력 안 했다면 경고
                    Hangil.showToastLong(this, "출발지와 도착지를 모두 입력해주세요!");
                    return;
                }

                if (isStartIsOutdoor) {
                    // 외부 -> 내부 안내
                    // 기존대로 안내
                    Hangil.suggestGuideDialog(
                            this,
                            String.format("현재 위치에서 %s %s까지 안내할까요?",
                                    endRoomData.getBuildingName(),
                                    endRoomData.getNode().getName()
                            ),
                            () -> {
                                // 긍정
                                onGuideSetting();
                            },
                            null
                    );
                } else {
                    // 내부 -> 내부 안내
                    // 내부 지도로 전환 제안
                    suggestIndoorGuideDialog();
                    IndoorActivity.setStartRoomData(startRoomData);
                    IndoorActivity.setEndRoomData(endRoomData);
                }
            });

            // SearchActivity의 목록에서 '선택'버튼을 누르게 되면 호출
            onOutdoorSelectRoomCallback = ((isStartRoom,
                                                    selectedSearchRoomData) -> {
                String buildingName = selectedSearchRoomData.getBuildingName();
                String roomName = selectedSearchRoomData.getNode().getName();
                String desc = String.format("[%s] %s", buildingName, roomName);
                if (isStartRoom) {
                    // 출발지 검색 일때
                    startRoomData = selectedSearchRoomData;
                    startInput.setText(desc);
                } else {
                    // 도착지 검색 일때
                    endRoomData = selectedSearchRoomData;
                    endInput.setText(desc);
                }
            });
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

    private void drawPathBuilding(TMapPoint start, int buildingId) {
        Building building = DataManager.buildingByIdMap.get(buildingId);
        TMapPoint dest = new TMapPoint(building.getLatitude(), building.getLongitude());
        tMap.findPath(start, dest, (tMapPolyLine -> {
            tMap.drawPathPolyLine(tMapPolyLine);
        }));
    }

    private void erasePathBuilding() {
        tMap.erasePolyLine();
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
    private void suggestIndoorGuideDialog() {
        // 실내 지도로 전환 요청
        Hangil.suggestGuideDialog(
                this,
                "선택한 입구를 출발지로 하는 실내 지도로 전환할까요?",
                () -> {
                    // 긍정
                    // 현재 안내 종료
                    Intent intent = new Intent(getApplicationContext(), IndoorActivity.class);

                    // 실내 지도 Activity에 건물 번호 전달
                    intent.putExtra(BUILDING_ID, startRoomData.getBuildingId());

                    // 출발 노드 전달
                    intent.putExtra(START_NODE_FLOOR,
                            startRoomData.getNode().getFloor());
                    intent.putExtra(START_NODE_NUMBER,
                            startRoomData.getNode().getNumber());
                    intent.putExtra(START_NODE_ID,
                            startRoomData.getNode().getId());

                    // 도착 노드 전달
                    intent.putExtra(END_NODE_FLOOR,
                            endRoomData.getNode().getFloor());
                    intent.putExtra(END_NODE_NUMBER,
                            endRoomData.getNode().getNumber());
                    intent.putExtra(END_NODE_ID,
                            endRoomData.getNode().getId());
                    intent.putExtra(END_NODE_NAME,
                            endRoomData.getNode().getName());
                    startActivity(intent);

                    // 안내 종료
                    offGuideSetting();
                },
                null
        );
    }
    private void offGuideSetting() {
        // 티맵 가이드모드 종료 및 검색창 활성화
        tMap.offGuideMode();

        // 검색창 바꾸기
        startInput.setText("");
        endInput.setText("");

        // 티맵 입구 마커 삭제
        removeMarkerEntrace(endRoomData.getBuildingId());

        // 경로 삭제
        erasePathBuilding();

        // 안내 중인 건물 없음으로 설정
        startRoomData = null;
        endRoomData = null;
        isStartIsOutdoor = false;
    }

    // 외부 -> 내부
    private void onGuideSetting() {
        // 현재 안내중인 노드 설정

        // 티맵 안내 모드 시작
        tMap.onGuideMode();

        // 토스트 메세지
        Hangil.showToastLong(this, "안내를 종료하려면 검색창을 터치하세요!");

        // 경로 생성
        drawPathBuilding(tMap.getLocationPoint(), endRoomData.getBuildingId());

        // 입구 마커 생성
        addMarkerEntrance(endRoomData.getBuildingId());
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
    public void onPressDown(ArrayList<TMapMarkerItem> tMapMarkerItems, ArrayList<TMapPOIItem> poiItems,
                            TMapPoint tMapPoint, PointF pointF) {
        if (tMapMarkerItems.isEmpty()) {
            // 터치한 마커가 없다면
            return;
        } else {
            // 외부 -> 내부 안내
            for (TMapMarkerItem selectedTmapMarkerItem : tMapMarkerItems) {
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
                                dataManager.requestGetNodeById(entrance.getNodeId(),
                                        (nodeDetail) -> {
                                    startRoomData = new SearchRoomData(
                                            new Node(
                                                    entrance.getNodeId(),
                                                    nodeDetail.getNumber(),
                                                    nodeDetail.getFloor(),
                                                    nodeDetail.getName(),
                                                    nodeDetail.getDescription()
                                            ),
                                            buildingInfo.id,
                                            nodeDetail.getBuilding()
                                    );
                                    IndoorActivity.setStartRoomData(startRoomData);
                                    IndoorActivity.setEndRoomData(endRoomData);
                                    suggestIndoorGuideDialog();
                                });
                            ++add;
                        }
                    }
                    return;
                }
            }
        }
    }

    @Override
    public void onPressUp(ArrayList<TMapMarkerItem> tMapMarkerItems, ArrayList<TMapPOIItem> poiItems,
                          TMapPoint tMapPoint, PointF pointF) {

    }
}

