package com.example.hangil_app;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hangil_app.api.DataManager;
import com.example.hangil_app.api.NodeType;
import com.example.hangil_app.api.response.Node;
import com.example.hangil_app.system.Hangil;
import com.example.hangil_app.search.OnClickStartGuideButton;
import com.example.hangil_app.search.OnStartGuideCallback;
import com.example.hangil_app.search.SearchRoomAdapter;
import com.example.hangil_app.search.SearchRoomData;
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
        dataManager.requestGetNodes(1, NodeType.ROOM, (nodes -> {
            List<SearchRoomData> searchRoomDataList = new ArrayList<>();
            for (Node node : nodes) {
                searchRoomDataList.add(new SearchRoomData(
                        node.getId(),
                        node.getName(),
                        dataManager.buildingByIdMap.get(1).getName()
                    )
                );
                Log.d(Hangil.API, dataManager.buildingByIdMap.get(1).getName());
            }
            searchTextView.setText(search);
            countTextView.setText(String.format("%d건의 검색결과가 존재합니다.", searchRoomAdapter.getItemCount()));
            searchRV.setAdapter(searchRoomAdapter);
            searchRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            searchRoomAdapter.setSearchRoomDatas(searchRoomDataList);
        }));
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