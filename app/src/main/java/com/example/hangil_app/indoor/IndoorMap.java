package com.example.hangil_app.indoor;

import static com.example.hangil_app.system.Key.API_URL;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.View;

import com.example.hangil_app.data.BuildingBackground;
import com.example.hangil_app.data.BuildingInfo;
import com.example.hangil_app.data.DataManager;
import com.example.hangil_app.data.OnPositionGetListener;
import com.example.hangil_app.data.api.dto.BuildingSignals;
import com.example.hangil_app.data.api.dto.IndoorPath;
import com.example.hangil_app.data.api.dto.PathNode;
import com.example.hangil_app.data.api.dto.Signal;
import com.example.hangil_app.data.api.dto.StartEndNode;
import com.example.hangil_app.system.Hangil;
import com.example.hangil_app.wifi.WifiHelper;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class IndoorMap {
    public static IndoorMap indoorMap;
    private final View indoorMapView;
    private final int NONE = -1;
    private final WifiHelper wifiHelper;
    private final DataManager dataManager;
    private final Context context;
    private final float screenWidth;
    private final float screenHeight;
    @Getter @Setter
    private boolean isIndoorGuideMode = false;
    @Getter @Setter
    private boolean canWifiScan = false;
    private int lastFloor = NONE;
    private boolean isWifiScanSuccessFirst = false;
    public static IndoorMap getInstance(Context context, IndoorMapView indoorMapView,
                                        float screenWidth,
                                        float screenHeight) {
        if (indoorMap == null) {
            return indoorMap = new IndoorMap(context, indoorMapView, screenWidth, screenHeight);
        } else {
            return indoorMap;
        }
    }
    private IndoorMap(Context context, IndoorMapView indoorMapView, float screenWidth,
                      float screenHeight) {
        this.context = context;
        this.indoorMapView = indoorMapView;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        wifiHelper = WifiHelper.getInstance(context);
        dataManager = DataManager.getInstance(API_URL);
        BuildingBackground.setContext(context);
        init();
    }
    public void startIndoorGuide(StartEndNode startEndNode, OnWifiScanSuccessFirstListener onWifiScanSuccessFirstListener) {
        // 와이파이 스캔 첫 성공 대기상태로 표시
        isWifiScanSuccessFirst = false;

        // 안내모드 중으로 표시
        isIndoorGuideMode = true;

        // 와이파이 스캔 시작
        canWifiScan = true;

        // 실내 경로 가져오기
        dataManager.requestGetIndoorPath(startEndNode, (indoorPath) -> {
            // 스캔 후 현재 위치 가져오기
            startRequestGetPositionLoop(startEndNode.getBuildingId(), (position) -> {
                // 와이파이 첫 스캔이라면 성공으로 표시
                if (!isWifiScanSuccessFirst) {
                    isWifiScanSuccessFirst = true;
                    onWifiScanSuccessFirstListener.onWifiScanSuccess();
                }

                int currNumber = position.getNumber();
                int currFloor = position.getFloor();
                int buildingId = startEndNode.getBuildingId();
                int startNodeNumber = startEndNode.getStartNodeNumber();
                int startNodeFloor = startEndNode.getStartNodeFloor();
                int endNodeNumber = startEndNode.getEndNodeNumber();
                int endNodeFloor = startEndNode.getEndNodeFloor();

                // 토스트 위치 알람
                Hangil.showToastLong(context.getApplicationContext(), String.format("%s층, %s번",
                        position.getFloor(), position.getNumber()));

                // 배경 업데이트
                if (lastFloor != currFloor) updateBackground(buildingId, currFloor);

                // 만약 안내 모드라면
                if (isIndoorGuideMode) {
                    // 도착 마커 생성
                    if (currFloor == endNodeFloor) {
                        addEndMarker(buildingId, endNodeFloor, endNodeNumber);
                    } else {
                        removeEndMarker();
                    }

                    // 카메라 조정

                    // 경로 그리기
                    addPath(buildingId, currFloor, indoorPath);
                }

                // 내 위치 조정
                moveMeCoord(buildingId, currFloor, currNumber);

                // 최신 층 업데이트
                lastFloor = currFloor;
                invalidateIndoor();
            });
        });
    }

    public void stopIndoorGuide() {
        setIndoorGuideMode(false);
        removePath();
        removeEndMarker();
    }

    private void addPath(int buildingId, int floor, IndoorPath indoorPath) {
        List<Coord> validCoords = new ArrayList<>();
        for (PathNode pathNode : indoorPath.getPath()) {
            int currFloor = pathNode.getFloor();
            int currNumber = pathNode.getNumber();
            if (currFloor == floor) {
                Coord coord =
                        BuildingInfo.findBuildingInfo(buildingId).coordByNumber[floor].get(currNumber);
                validCoords.add(coord);
            }
        }
        IndoorMapView.setPath(validCoords);
    }

    private void removePath() {
        IndoorMapView.setPath(new ArrayList<>());
    }

    private void init() {

    }

    private void setCameraToMe() {
        if (IndoorMapView.getMeCoord() == null) return;
        Coord currMe = IndoorMapView.getMeCoord();
        IndoorMapView.setOffsetX(currMe.getX() - screenWidth / 2);
        IndoorMapView.setOffsetY(currMe.getY() - screenHeight / 2);
    }

    private void invalidateIndoor() {
        indoorMapView.invalidate();
    }

    private void moveMeCoord(int buildingId, int floor, int number) {
        Coord newCoord = BuildingInfo.findBuildingInfo(buildingId).coordByNumber[floor].get(number);
        IndoorMapView.setMeCoord(newCoord);
    }

    private void addEndMarker(int buildingId, int floor, int number) {
        Coord newCoord = BuildingInfo.findBuildingInfo(buildingId).coordByNumber[floor].get(number);
        IndoorMapView.setEndMarkerCoord(newCoord);
    }

    private void removeEndMarker() {
        IndoorMapView.setEndMarkerCoord(null);
    }

    private void updateBackground(int buildingId, int floor) {
        IndoorMapView.setBackground(
                BuildingBackground.getBitmaps()[buildingId][floor]
        );
    }

    private void startRequestGetPositionLoop(int buildingId,
                                             OnPositionGetListener onPositionGetListener) {
        // 실내 안내 모드가 꺼지면 종료
        if (!canWifiScan) return;
        wifiHelper.scanWifi((scanResults) -> {
            // 스캔에 성공하면
            // 와이파이 스캔 결과를 Signal로 매핑
            List<Signal> newSignals = new ArrayList<>();
            for (ScanResult scanResult : scanResults) {
                newSignals.add(
                        new Signal(
                                scanResult.SSID,
                                scanResult.BSSID,
                                scanResult.level //RSSI
                        )
                );
            }

            // 매핑된 Signal 정보로 위치 API 요청
            BuildingSignals newBuildingSignals = new BuildingSignals(buildingId, newSignals);
            dataManager.requestGetPosition(newBuildingSignals, (position) -> {
                if (!canWifiScan) return;
                // 위치 전달
                onPositionGetListener.onPositionGet(position);
                // 반복
                startRequestGetPositionLoop(buildingId, onPositionGetListener);
            });
        });
    }
}
