package com.example.hangil_app.data;

import com.example.hangil_app.data.api.dto.Building;

import java.util.List;

@FunctionalInterface
public interface OnBuildingsReadyListener {
    void onBuildingsReady(List<Building> buildings);
}
