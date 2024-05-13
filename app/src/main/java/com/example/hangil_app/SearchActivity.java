package com.example.hangil_app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private final TMap tMap = TMap.getTmap(); // 웬만하면 쓰지말자.
    @Setter
    private static OnStartGuideCallback onStartGuideCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //
        searchRV = findViewById(R.id.searchRV);
        searchTextView = findViewById(R.id.searchTextView);
        countTextView = findViewById(R.id.countTextView);

        searchRoomAdapter = new SearchRoomAdapter(startGuideButtonClick());

        List<SearchRoomData> searchRoomDataList = new ArrayList<>();
        String search = getIntent().getStringExtra(Hangil.SEARCH);


        searchRV.setAdapter(searchRoomAdapter);
        searchRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("402호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호", "다산정보관"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관이구요 입니다 입니다만 텍스트 크기가 매우 클 ㅓㄱㅅ으롱ㄷㅈㄹㄷㅈㄹ"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호ㅈㄷㄹㄷㅈㄹㄷㅈㄹㄷㄹㅈㄹㄷㅈㄹㅈㄷㄹㅈㄷㄹ", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));
        searchRoomDataList.add(new SearchRoomData("404호", "공학 2관"));


        searchRoomAdapter.setSearchRoomDatas(searchRoomDataList);

        searchTextView.setText(search);
        countTextView.setText(String.format("%d건의 검색결과가 존재합니다.", searchRoomAdapter.getItemCount()));
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