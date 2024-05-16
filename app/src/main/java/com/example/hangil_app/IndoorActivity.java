package com.example.hangil_app;

import static com.example.hangil_app.system.Hangil.API;
import static com.example.hangil_app.system.Hangil.API_URL;
import static com.example.hangil_app.system.Hangil.BUILDING_ID;
import static com.example.hangil_app.system.Hangil.END_NODE;
import static com.example.hangil_app.system.Hangil.START_NODE;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hangil_app.data.DataManager;
import com.example.hangil_app.data.api.dto.BuildingSignals;
import com.example.hangil_app.data.api.dto.Signal;
import com.example.hangil_app.wifi.WifiHelper;

import java.util.ArrayList;
import java.util.List;

public class IndoorActivity extends AppCompatActivity {
    private final DataManager dataManager = DataManager.getInstance(API_URL);
    private boolean isIndoorGuideMode = false;
    private int buildingId;
    private int startNode;
    private int endNode;
    private WifiHelper wifiHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor);

        // 건물, 출발지, 도착지 받기
        buildingId = getIntent().getIntExtra(BUILDING_ID, 0);
        startNode = getIntent().getIntExtra(START_NODE, -1);
        endNode = getIntent().getIntExtra(END_NODE, -1);

        //
        Button f1 = findViewById(R.id.f1Btn);
        Button f2 = findViewById(R.id.f2Btn);
        Button f3 = findViewById(R.id.f3Btn);
        Button f4 = findViewById(R.id.f4Btn);

        //
        wifiHelper = WifiHelper.getInstance(this);
        isIndoorGuideMode = true;
        startRequestGetPositionLoop();
    }

    private void startRequestGetPositionLoop() {
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
                // 현재 위치 받아왔다면
                Log.d(API, String.format("현재 위치 : %s층 %s번", position.getFloor(), position.getFloor()));

                // 반복
                startRequestGetPositionLoop();
            });
        });
    }
}