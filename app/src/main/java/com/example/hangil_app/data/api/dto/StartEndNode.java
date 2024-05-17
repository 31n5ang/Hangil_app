package com.example.hangil_app.data.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StartEndNode {
    private int buildingId;
    private int startNodeNumber;
    private int startNodeFloor;
    private int endNodeNumber;
    private int endNodeFloor;
}
