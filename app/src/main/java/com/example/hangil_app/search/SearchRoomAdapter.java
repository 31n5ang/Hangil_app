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

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class SearchRoomAdapter extends RecyclerView.Adapter<SearchRoomAdapter.RecyclerViewHolder>{
    @Getter @Setter
    private List<SearchRoomData> searchRoomDatas = new ArrayList<>();
    private final OnClickStartGuideButton onStartGuideCallback;
    public SearchRoomAdapter(OnClickStartGuideButton onStartGuideCallback) {
        this.onStartGuideCallback = onStartGuideCallback;
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView roomName;
        private TextView roomDetail;
        private Button startGuideBtn;
        private MainActivity mainActivity;

        public RecyclerViewHolder(@NonNull View view) {
            super(view);
            roomName = view.findViewById(R.id.roomName);
            roomDetail = view.findViewById(R.id.buildingName);
            startGuideBtn = view.findViewById(R.id.startGuideBtn);
        }

        void onBind(SearchRoomData searchRoomData) {
            roomName.setText(searchRoomData.getNode().getName());
            roomDetail.setText(searchRoomData.getBuildingName());

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
            // 안내 모드 클릭 시 클릭한 searchRoomData 객체 전달
            onStartGuideCallback.onClickStartGuideButton(searchRoomDatas.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return searchRoomDatas.size();
    }
}
