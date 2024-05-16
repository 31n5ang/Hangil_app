package com.example.hangil_app.data.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuildingSignals {
    private int buildingId;
    private List<Signal> signals;
}
