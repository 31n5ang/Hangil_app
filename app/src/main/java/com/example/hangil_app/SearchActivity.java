package com.example.hangil_app;

import static com.example.hangil_app.data.DataManager.BUILDING_COUNT;
import static com.example.hangil_app.data.DataManager.MIN_BUILDING_INDEX;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hangil_app.data.DataManager;
import com.example.hangil_app.data.NodeType;
import com.example.hangil_app.data.api.response.Node;
import com.example.hangil_app.search.OnClickStartGuideButton;
import com.example.hangil_app.search.OnStartGuideCallback;
import com.example.hangil_app.search.SearchRoomAdapter;
import com.example.hangil_app.search.SearchRoomData;
import com.example.hangil_app.system.Hangil;
import com.example.hangil_app.tmap.TMap;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

public class SearchActivity extends AppCompatActivity {
    private SearchRoomAdapter searchRoomAdapter;
    private RecyclerView searchRV;
    private TextView searchTextView, countTextView;
    private final TMap tMap = TMap.getTmap();
    private final DataManager dataManager = DataManager.getInstance(Hangil.API_URL);
    @Setter
    private static OnStartGuideCallback onStartGuideCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //
        String search = getIntent().getStringExtra(Hangil.SEARCH);

        searchRV = findViewById(R.id.searchRV);
        searchTextView = findViewById(R.id.searchTextView);
        countTextView = findViewById(R.id.countTextView);

        searchRoomAdapter = new SearchRoomAdapter(startGuideButtonClick());

        // searchRoomDataList을 ROOM 노드로 채워넣기
        List<SearchRoomData> searchRoomDataList = new ArrayList<>();
        for (int i = MIN_BUILDING_INDEX; i < (MIN_BUILDING_INDEX + BUILDING_COUNT); i++) {
            final int buildingId = i;
            dataManager.requestGetNodes(buildingId, NodeType.ROOM, (nodes -> {
                for (Node node : nodes) {
                    searchRoomDataList.add(new SearchRoomData(
                                    node.getId(),
                                    buildingId,
                                    node.getName(),
                                    DataManager.buildingByIdMap.get(buildingId).getName()
                            )
                    );
                }
                // 불러오면 즉각 리스트 반영.
                // 모두 request 후 한 번만 반영하는 방법도 있겠지만..
                searchRV.setAdapter(searchRoomAdapter);
                searchRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                searchRoomAdapter.setSearchRoomDatas(searchRoomDataList);
                searchTextView.setText(search);
                countTextView.setText(String.format("%d건의 검색결과가 존재합니다.", searchRoomAdapter.getItemCount()));
            }));
        }
    }

    private OnClickStartGuideButton startGuideButtonClick() {
        return new OnClickStartGuideButton() {
            @Override
            public void onClickStartGuideButton(SearchRoomData searchRoomData) {
                // 전달 받은 SearchRoomData 객체를 MainActivity로 전달
                onStartGuideCallback.startGuide(searchRoomData);
                finish();
            }
        };
    }
}