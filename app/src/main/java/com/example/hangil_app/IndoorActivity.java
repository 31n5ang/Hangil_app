package com.example.hangil_app;

import static com.example.hangil_app.data.DataManager.BUILDING_COUNT;
import static com.example.hangil_app.data.DataManager.MIN_BUILDING_INDEX;
import static com.example.hangil_app.system.Hangil.API;
import static com.example.hangil_app.system.Hangil.API_URL;
import static com.example.hangil_app.system.Hangil.BUILDING_ID;
import static com.example.hangil_app.system.Hangil.END_NODE_FLOOR;
import static com.example.hangil_app.system.Hangil.END_NODE_ID;
import static com.example.hangil_app.system.Hangil.END_NODE_NAME;
import static com.example.hangil_app.system.Hangil.END_NODE_NUMBER;
import static com.example.hangil_app.system.Hangil.START_NODE_FLOOR;
import static com.example.hangil_app.system.Hangil.START_NODE_NUMBER;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hangil_app.data.BuildingBackground;
import com.example.hangil_app.data.BuildingInfo;
import com.example.hangil_app.data.DataManager;
import com.example.hangil_app.data.api.dto.BuildingSignals;
import com.example.hangil_app.data.api.dto.Signal;
import com.example.hangil_app.data.api.dto.StartEndNode;
import com.example.hangil_app.indoor.IndoorMapView;
import com.example.hangil_app.system.Hangil;
import com.example.hangil_app.wifi.WifiHelper;

import java.util.ArrayList;
import java.util.List;

public class IndoorActivity extends AppCompatActivity {
    private final DataManager dataManager = DataManager.getInstance(API_URL);
    private WifiHelper wifiHelper;
    private boolean isIndoorGuideMode = false;
    private int buildingId;
    private int startNodeFloor;
    private int startNodeNumber;
    private int startNodeId;
    private int endNodeFloor;
    private int endNodeNumber;
    private int endNodeId;
    private StartEndNode startEndNode;
    private String endNodeName;
    private View indoorMapView;
    private TextView currLoc;
    private int lastFloor = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor);

        // initialize
        BuildingBackground.setContext(this);
        initExtras();
        wifiHelper = WifiHelper.getInstance(this);
        indoorMapView = findViewById(R.id.indoorMapView);

        // 엑스트라 값을 잘 받았는지 확인
        if (!isValidNode(buildingId, startNodeNumber, endNodeNumber)) {
            finish();
        }

        // TODO (시작, 끝)으로 안내 하도록 함수로 만들기 - 토
        // TODO 검색 필터링 (건물 토글 추가 및 검색 필터) - 토
        // TODO 내부 에서 검색 시 : 현재 위치가 출발로 되고, 도착지는 건물 내부 목록에서만 선택 가능 - 토
        // TODO 외부 에서 검색 시 : 기존대로 안내 - 토

        // 실내 초기 내 위치 시작으로 설정
        IndoorMapView.meCoord =
                findBuildingInfo(buildingId).coordByNumber[startNodeFloor].get(startNodeNumber);
        lastFloor = startNodeFloor;
        IndoorMapView.background = BuildingBackground.getBitmaps()[buildingId][startNodeFloor];
        IndoorMapView.offsetY =
                findBuildingInfo(buildingId).coordByNumber[startNodeFloor].get(startNodeNumber).getY();
        IndoorMapView.offsetX =
                findBuildingInfo(buildingId).coordByNumber[startNodeFloor].get(startNodeNumber).getX();

        // 출발지 부터 도착지 까지 경로 API 요청
        dataManager.requestGetIndoorPath(startEndNode,
                (indoorPath) -> {
            Log.d(API, indoorPath.getPath().toString());
            // 트래킹 모드 시작
            isIndoorGuideMode = true;
            startRequestGetPositionLoop((position) -> {
                Log.d(API, position.getFloor() + "층, " + position.getNumber() + "번");
                // 현재 위치 받아왔다면
                Hangil.showToastLong(this, String.format("현재 위치 : %s층 %s번", position.getFloor(),
                        position.getNumber()));

                int currNumber = position.getNumber();
                int currFloor = position.getFloor();

                // 현재 위치 텍스트뷰 갱신

                // TODO 배경 변경
                if (lastFloor != currFloor) { //
                    IndoorMapView.background = BuildingBackground.getBitmaps()[buildingId][currFloor];
                }

                // TODO 도착지 마커 생성
                if (currFloor == endNodeFloor) {
                    IndoorMapView.endMarkerCoord =
                            findBuildingInfo(buildingId).coordByNumber[currFloor].get(endNodeNumber);
                } else {
                    // 현재 층이 아니라면 숨기기
                    IndoorMapView.endMarkerCoord = null;
                }

                // TODO 내 위치 마커 옮김
                IndoorMapView.meCoord =
                        findBuildingInfo(buildingId).coordByNumber[currFloor].get(currNumber);

                // 최근 층 갱신
                lastFloor = currFloor;

                // 그리기
                indoorMapView.invalidate();
            });
        });
    }

    private void startRequestGetPositionLoop(OnPositionGetListener onPositionGetListener) {
        // 실내 안내 모드가 꺼지면 종료
        if (!isIndoorGuideMode) return;
        wifiHelper.scanWifi((scanResults) -> {
            // 스캔에 성공하면
            // 와이파이 스캔 결과를 Signal로 매핑
            List<Signal> newSignals = new ArrayList<>();
            for (ScanResult scanResult : scanResults) {
                newSignals.add(
                        new Signal(
                                scanResult.SSID,
                                scanResult.BSSID,
                                scanResult.level
                        )
                );
            }

            // 매핑된 Signal 정보로 위치 API 요청
            BuildingSignals newBuildingSignals = new BuildingSignals(buildingId, newSignals);
            dataManager.requestGetPosition(newBuildingSignals, (position) -> {
                // 위치 전달
                onPositionGetListener.onPositionGet(position);
                // 반복
                startRequestGetPositionLoop(onPositionGetListener);
            });
        });
    }

    private boolean isValidNode(int buildingId, int startNode, int endNode) {
        boolean isValidBuilding =
                MIN_BUILDING_INDEX <= buildingId && buildingId <= MIN_BUILDING_INDEX + BUILDING_COUNT;
        boolean isValidNode = startNode >= 1 && endNode >= 1;
        return isValidBuilding && isValidNode;
    }

    private void initExtras() {
        // 건물, 출발지, 도착지 받기
        buildingId = getIntent().getIntExtra(BUILDING_ID, 0);
        startNodeFloor = getIntent().getIntExtra(START_NODE_FLOOR, -1);
        startNodeNumber = getIntent().getIntExtra(START_NODE_NUMBER, -1);
        endNodeFloor = getIntent().getIntExtra(END_NODE_FLOOR, -1);
        endNodeNumber = getIntent().getIntExtra(END_NODE_NUMBER, -1);
        endNodeId = getIntent().getIntExtra(END_NODE_ID, -1);
        endNodeName = getIntent().getStringExtra(END_NODE_NAME);
        startEndNode = new StartEndNode(
                buildingId,
                startNodeNumber,
                startNodeFloor,
                endNodeNumber,
                endNodeFloor
        );
    }

    public BuildingInfo findBuildingInfo(int buildingId) {
        for (BuildingInfo buildingInfo : BuildingInfo.values()) {
            if (buildingInfo.id == buildingId) {
                return buildingInfo;
            }
        }
        return BuildingInfo.NONE;
    }

    @Override
    public void onBackPressed() {
        // 뒤로가기 누르면 안내 종료
        isIndoorGuideMode = false;
        super.onBackPressed();
    }
}