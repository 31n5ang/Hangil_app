package com.example.hangil_app.search;

import com.example.hangil_app.data.api.dto.Node;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchRoomData {
    private Node node;
    private int buildingId;
    private String buildingName;
}
