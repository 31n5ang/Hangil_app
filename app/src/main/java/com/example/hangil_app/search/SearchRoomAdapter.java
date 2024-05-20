package com.example.hangil_app.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hangil_app.MainActivity;
import com.example.hangil_app.R;
import com.example.hangil_app.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class SearchRoomAdapter extends RecyclerView.Adapter<SearchRoomAdapter.RecyclerViewHolder>{
    @Getter @Setter
    private List<SearchRoomData> searchRoomDatas = new ArrayList<>();
    private final OnClickSelectLocBtnListener onClickSelectLocBtnCallback;
    public SearchRoomAdapter(OnClickSelectLocBtnListener onStartGuideCallback) {
        this.onClickSelectLocBtnCallback = onStartGuideCallback;
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView roomName;
        private TextView buildingName;
        private TextView roomDescription;
        private Button startGuideBtn;

        public RecyclerViewHolder(@NonNull View view) {
            super(view);
            roomName = view.findViewById(R.id.roomName);
            buildingName = view.findViewById(R.id.buildingName);
            startGuideBtn = view.findViewById(R.id.selectLocBtn);
            roomDescription = view.findViewById(R.id.roomDescription);
        }

        void onBind(SearchRoomData searchRoomData) {
            roomName.setText(searchRoomData.getNode().getName());
            buildingName.setText(searchRoomData.getBuildingName());
            roomDescription.setText(" "+searchRoomData.getNode().getDescription());
        }
    }

    @NonNull
    @Override
    public SearchRoomAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new SearchRoomAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRoomAdapter.RecyclerViewHolder holder, int position) {
        holder.onBind(searchRoomDatas.get(position));
        holder.startGuideBtn.setOnClickListener((event) -> {
            // 선택 클릭 시 클릭한 searchRoomData 객체 전달
            // 선택했다면 출발지는 외부가 아님을 알림
            if (SearchActivity.isStartRoom()) {
                MainActivity.setStartIsOutdoor(false);
            }
            onClickSelectLocBtnCallback.onClickSelectLocBtn(searchRoomDatas.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return searchRoomDatas.size();
    }
}
