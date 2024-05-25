package com.example.hangil_app;

import static com.example.hangil_app.system.Hangil.BUILDING_COUNT;
import static com.example.hangil_app.system.Hangil.BUILDING_ID;
import static com.example.hangil_app.system.Hangil.BUILDING_NAME;
import static com.example.hangil_app.system.Hangil.END_NODE_DESC;
import static com.example.hangil_app.system.Hangil.END_NODE_FLOOR;
import static com.example.hangil_app.system.Hangil.END_NODE_ID;
import static com.example.hangil_app.system.Hangil.END_NODE_NAME;
import static com.example.hangil_app.system.Hangil.END_NODE_NUMBER;
import static com.example.hangil_app.system.Hangil.GUIDE_TYPE;
import static com.example.hangil_app.system.Hangil.INDOOR_GUIDE;
import static com.example.hangil_app.system.Hangil.MIN_BUILDING_INDEX;
import static com.example.hangil_app.system.Hangil.SEARCH_TYPE;
import static com.example.hangil_app.system.Hangil.SEARCH_TYPE_END;
import static com.example.hangil_app.system.Hangil.SEARCH_TYPE_START;
import static com.example.hangil_app.system.Hangil.START_NODE_DESC;
import static com.example.hangil_app.system.Hangil.START_NODE_FLOOR;
import static com.example.hangil_app.system.Hangil.START_NODE_ID;
import static com.example.hangil_app.system.Hangil.START_NODE_NAME;
import static com.example.hangil_app.system.Hangil.START_NODE_NUMBER;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hangil_app.data.api.dto.Node;
import com.example.hangil_app.data.api.dto.StartEndNode;
import com.example.hangil_app.indoor.IndoorMap;
import com.example.hangil_app.indoor.IndoorMapView;
import com.example.hangil_app.indoor.OnPositionStairListener;
import com.example.hangil_app.search.OnSelectRoomCallback;
import com.example.hangil_app.search.SearchRoomData;
import com.example.hangil_app.system.Hangil;
import com.example.hangil_app.system.ProgressSpin;

import lombok.Getter;

public class IndoorActivity extends AppCompatActivity {
    private TextView endInput;
    private TextView startInput;
    private ImageView currLocBtn;
    private ImageView startGuideBtn;
    private IndoorMapView indoorMapView;
    private Button goOutdoorBtn;
    private IndoorMap indoorMap;
    private TextView stairText;
    private ConstraintLayout stairForm;
    private int buildingId;
    private String buildingName;
    private int startNodeFloor;
    private int startNodeNumber;
    private int startNodeId;
    private String startNodeName;
    private String startNodeDesc;
    private int endNodeFloor;
    private int endNodeNumber;
    private int endNodeId;
    private String endNodeName;
    private String endNodeDesc;
    private StartEndNode startEndNode;
    private SearchRoomData startRoomData;
    private SearchRoomData endRoomData;
    @Getter
    private static OnSelectRoomCallback onIndoorSelectRoomCallback;
    private ProgressSpin progressSpin;
    @Getter
    private static OnPositionStairListener onPositionStairListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor);

        // initialize
        startInput = findViewById(R.id.indoorStartInput);
        endInput = findViewById(R.id.indoorEndInput);
        startGuideBtn = findViewById(R.id.indoorStartGuideBtn);
        goOutdoorBtn = findViewById(R.id.goOutdoorBtn);
        stairText = findViewById(R.id.stairText);
        stairForm = findViewById(R.id.stairForm);

        progressSpin = new ProgressSpin(this);
        initExtras();

        // 계단일 때 콜백 처리
        stairText.setText(String.format("계단을 오르내리는 중 입니다..\n%s층으로 향해주세요!", endRoomData.getNode().getFloor()));
        onPositionStairListener = (isStair) -> {
            if (isStair) {
                stairForm.setVisibility(View.VISIBLE);
            } else {
                stairForm.setVisibility(View.GONE);
            }
        };


        LinearLayout container = findViewById(R.id.indoorMapViewContainer);
        indoorMapView = new IndoorMapView(IndoorActivity.this);
        indoorMapView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        container.addView(indoorMapView);

        //

        String startText = String.format("[%s] %s", startRoomData.getBuildingName(),
                startRoomData.getNode().getName());
        String endText = String.format("[%s] %s", endRoomData.getBuildingName(),
                endRoomData.getNode().getName());

        startInput.setText(startText);
        endInput.setText(endText);

        // 엑스트라 값을 잘 받았는지 확인
        if (!isValidNode(buildingId, startNodeNumber, endNodeNumber) || startRoomData == null || endRoomData == null) {
            finish();
        }

        // 초기 안내 시작
        onGuideSetting(startEndNode);

        initEvent();
    }


    private boolean isValidNode(int buildingId, int startNode, int endNode) {
        boolean isValidBuilding =
                MIN_BUILDING_INDEX <= buildingId && buildingId <= MIN_BUILDING_INDEX + BUILDING_COUNT;
        boolean isValidNode = startNode >= 1 && endNode >= 1;
        return isValidBuilding && isValidNode;
    }

    private void initExtras() {
        // 건물, 출발지, 도착지 받기
        Intent intent = getIntent();
        buildingId = intent.getIntExtra(BUILDING_ID, 0);
        buildingName = intent.getStringExtra(BUILDING_NAME);

        startNodeId =  intent.getIntExtra(START_NODE_ID, -1);
        startNodeFloor = intent.getIntExtra(START_NODE_FLOOR, -1);
        startNodeNumber = intent.getIntExtra(START_NODE_NUMBER, -1);
        startNodeName = intent.getStringExtra(START_NODE_NAME);
        startNodeDesc = intent.getStringExtra(START_NODE_DESC);

        endNodeFloor = intent.getIntExtra(END_NODE_FLOOR, -1);
        endNodeNumber = intent.getIntExtra(END_NODE_NUMBER, -1);
        endNodeId = intent.getIntExtra(END_NODE_ID, -1);
        endNodeName = intent.getStringExtra(END_NODE_NAME);
        endNodeDesc = intent.getStringExtra(END_NODE_DESC);


        startEndNode = new StartEndNode(
                buildingId,
                startNodeNumber,
                startNodeFloor,
                endNodeNumber,
                endNodeFloor
        );

        startRoomData = new SearchRoomData(
                new Node(
                        startNodeId,
                        startNodeNumber,
                        startNodeFloor,
                        startNodeName,
                        startNodeDesc
                ),
                buildingId,
                buildingName
        );

        endRoomData = new SearchRoomData(
                new Node(
                        endNodeId,
                        endNodeNumber,
                        endNodeFloor,
                        endNodeName,
                        endNodeDesc
                ),
                buildingId,
                buildingName
        );
    }

    private void initEvent() {
        // 나가기 버튼을 클릭하게 되면
        goOutdoorBtn.setOnClickListener((event) -> {
            escape();
        });

        // 출발지 텍스트뷰를 누르게 되면
        startInput.setOnClickListener((event) -> {
            if (indoorMap.isIndoorGuideMode()) {
                // 안내 중이라면 종료 안내
                suggestOffGuideDialog();
            } else {
                // SearchActivity로 이동
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra(SEARCH_TYPE, SEARCH_TYPE_START);
                intent.putExtra(GUIDE_TYPE, INDOOR_GUIDE);
                intent.putExtra(BUILDING_ID, buildingId);
                startActivity(intent);
            }
        });

        // 도착지 텍스트뷰를 누르게 되면
        endInput.setOnClickListener((event) -> {
            if (indoorMap.isIndoorGuideMode()) {
                // 안내 중이라면 종료 안내
                suggestOffGuideDialog();
            } else {
                // SearchActivity로 이동
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra(SEARCH_TYPE, SEARCH_TYPE_END);
                intent.putExtra(GUIDE_TYPE, INDOOR_GUIDE);
                intent.putExtra(BUILDING_ID, buildingId);
                startActivity(intent);
            }
        });

        // 안내 버튼을 누르게 되면
        startGuideBtn.setOnClickListener((event) -> {
            if (startRoomData == null || endRoomData == null) {
                // 출발지와 목적지를 입력하지 않았다면
                Hangil.showToastLong(this, "출발지와 도착지를 모두 입력해주세요!");
            } else {
                // 안내 시작 제안
                suggestOnGuideDialog();
            }
        });

        // SearchActivity의 목록에서 '선택'버튼을 누르게 되면 호출
        onIndoorSelectRoomCallback = ((isStartRoom,
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
    }



    private void escape() {
        indoorMap.stopIndoorGuide();
        finish();
    }

    private void onGuideSetting(StartEndNode startEndNode) {
        // 로딩 창 띄우기
        showProgressSpin();

        // 실내 지도 생성
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(size);
        indoorMap = IndoorMap.getInstance(getApplicationContext(), indoorMapView, size.x, size.y);

        // 실내 안내 시작
        indoorMap.startIndoorGuide(startEndNode, () -> {
            // 와이파이 스캔에 처음으로 성공 했다면
            // 로딩 창 숨기기
            hideProgressSpin();
        });
    }

    private void offGuideSetting() {
        // 안내 종료
        indoorMap.stopIndoorGuide();

        // 검색창 바꾸기
        startInput.setText("");
        endInput.setText("");

        // 안내 중인 건물 없음으로 설정
        startRoomData = null;
        endRoomData = null;
    }

    private void suggestOnGuideDialog() {
        // 안내를 위한 정보 입력
        StartEndNode newStartEndNode = new StartEndNode(
                startRoomData.getBuildingId(),
                startRoomData.getNode().getNumber(),
                startRoomData.getNode().getFloor(),
                endRoomData.getNode().getNumber(),
                endRoomData.getNode().getFloor()
        );

        // 안내 시작
        Hangil.suggestGuideDialog(
                this,
                String.format(
                        "%s에서 %s까지 안내할까요?", startRoomData.getNode().getName(),
                        endRoomData.getNode().getName()
                ),
                () -> {
                    // 긍정
                    onGuideSetting(newStartEndNode);
                },
                null
        );
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
        // 화면에 안 보이면
        // 와이파이 스캔 꺼두기
        super.onStop();
        indoorMap.setCanWifiScan(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        indoorMapView.invalidate();
    }
}