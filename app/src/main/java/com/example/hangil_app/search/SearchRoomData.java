package com.example.hangil_app.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchRoomData {
    int nodeId;
    int buildingId;
    String roomName;
    String roomDetail;
}
