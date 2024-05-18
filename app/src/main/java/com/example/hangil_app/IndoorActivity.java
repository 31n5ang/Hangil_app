package com.example.hangil_app;

import static com.example.hangil_app.data.DataManager.BUILDING_COUNT;
import static com.example.hangil_app.data.DataManager.MIN_BUILDING_INDEX;
import static com.example.hangil_app.system.Hangil.BUILDING_ID;
import static com.example.hangil_app.system.Hangil.END_NODE_FLOOR;
import static com.example.hangil_app.system.Hangil.END_NODE_ID;
import static com.example.hangil_app.system.Hangil.END_NODE_NAME;
import static com.example.hangil_app.system.Hangil.END_NODE_NUMBER;
import static com.example.hangil_app.system.Hangil.START_NODE_FLOOR;
import static com.example.hangil_app.system.Hangil.START_NODE_NUMBER;

import android.graphics.Point;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hangil_app.data.BuildingBackground;
import com.example.hangil_app.data.api.dto.StartEndNode;
import com.example.hangil_app.indoor.IndoorMap;
import com.example.hangil_app.indoor.IndoorMapView;

public class IndoorActivity extends AppCompatActivity {
    private IndoorMapView indoorMapView;
    private int buildingId;
    private int startNodeFloor;
    private int startNodeNumber;
    private int startNodeId;
    private int endNodeFloor;
    private int endNodeNumber;
    private int endNodeId;
    private StartEndNode startEndNode;
    private String endNodeName;
    private TextView currLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor);

        // initialize
        BuildingBackground.setContext(this);
        initExtras();

        // 엑스트라 값을 잘 받았는지 확인
        if (!isValidNode(buildingId, startNodeNumber, endNodeNumber)) {
            finish();
        }

        // TODO (시작, 끝)으로 안내 및 경로 그리도록 함수로 만들기 - 토
        // TODO 검색 필터링 (건물 토글 추가 및 검색 필터) - 토
        // TODO 내부 에서 검색 시 : 현재 위치가 출발로 되고, 도착지는 건물 내부 목록에서만 선택 가능 - 토
        // TODO 외부 에서 검색 시 : 기존대로 안내 - 토

        // 화면 크기 전달
        indoorMapView = findViewById(R.id.indoorMapView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(size);
        IndoorMap indoorMap = IndoorMap.getInstance(getApplicationContext(), indoorMapView, size.x, size.y);
        indoorMap.startIndoorGuide(startEndNode);
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
}