package com.example.hangil_app;

import static com.example.hangil_app.system.Hangil.MIN_BUILDING_INDEX;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hangil_app.data.BuildingInfo;
import com.example.hangil_app.data.DataManager;
import com.example.hangil_app.data.api.dto.Node;
import com.example.hangil_app.data.api.dto.Search;
import com.example.hangil_app.search.OnClickSelectLocBtnListener;
import com.example.hangil_app.search.SearchRoomAdapter;
import com.example.hangil_app.search.SearchRoomData;
import com.example.hangil_app.system.Hangil;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class SearchActivity extends AppCompatActivity {
    private SearchRoomAdapter searchRoomAdapter;
    private RecyclerView searchRV;
    private TextView countTextView;
    private EditText searchInput;
    private Spinner buildingSpinner;
    private final DataManager dataManager = DataManager.getInstance(Hangil.API_URL);
    @Getter
    private static boolean isStartRoom = false;
    @Getter
    private static boolean isIndoorGuide = false;
    private int buildingId = MIN_BUILDING_INDEX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //
        isStartRoom =
                getIntent().getStringExtra(Hangil.SEARCH_TYPE).equals(Hangil.SEARCH_TYPE_START);
        isIndoorGuide =
                getIntent().getStringExtra(Hangil.GUIDE_TYPE).equals(Hangil.INDOOR_GUIDE);
        buildingId = getIntent().getIntExtra(Hangil.BUILDING_ID, MIN_BUILDING_INDEX);


        searchRV = findViewById(R.id.searchRV);
        countTextView = findViewById(R.id.countTextView);
        searchInput = findViewById(R.id.searchInput);
        buildingSpinner = findViewById(R.id.buildingSpinner);

        searchRoomAdapter = new SearchRoomAdapter(startGuideButtonClick());

        //
        if (isIndoorGuide) {
            // 만약 indoor 안내라면 spinner 의미가 없음
            buildingSpinner.setSelection(buildingId - 1);
            buildingSpinner.setEnabled(false);
        } else {
            buildingSpinner.setEnabled(true);
            buildingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    buildingId = MIN_BUILDING_INDEX + position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

        // 초기엔 모두 불러오기
        setSearchList(new Search(buildingId, ""));

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 검색 텍스트가 바뀔때마다 불러오기
                setSearchList(new Search(buildingId, s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setSearchList(Search search) {
        int buildingId = search.getBuildingId();
        List<SearchRoomData> searchRoomDatas = new ArrayList<>();
        dataManager.requestGetNodesByName(search, (nodes -> {
            for (Node node : nodes.getNodes()) {
                searchRoomDatas.add(new SearchRoomData(
                                node,
                                buildingId,
                                BuildingInfo.findBuildingInfo(buildingId).name
                        )
                );
            }
            searchRV.setAdapter(searchRoomAdapter);
            searchRV.setLayoutManager(new LinearLayoutManager(this,
                    RecyclerView.VERTICAL,
                    false));
            searchRoomAdapter.setSearchRoomDatas(searchRoomDatas);
            countTextView.setText(String.format("%d건의 검색결과가 존재합니다.", searchRoomAdapter.getItemCount()));
        }));
    }

    private OnClickSelectLocBtnListener startGuideButtonClick() {
        return new OnClickSelectLocBtnListener() {
            @Override
            public void onClickSelectLocBtn(SearchRoomData searchRoomData) {
                // 전달 받은 SearchRoomData 객체를 MainActivity로 전달
                if (isIndoorGuide) {
                    IndoorActivity.getOnSelectRoomCallback().onSelectRoom(isStartRoom, searchRoomData);
                } else {
                    MainActivity.getOnOutdoorSelectRoomCallback().onSelectRoom(isStartRoom, searchRoomData);
                }
                finish();
            }
        };
    }
}