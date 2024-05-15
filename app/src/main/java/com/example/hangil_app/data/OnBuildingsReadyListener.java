package com.example.hangil_app.data;

import com.example.hangil_app.data.api.response.Building;

import java.util.List;

@FunctionalInterface
public interface OnBuildingsReadyListener {
    void onBuildingsReady(List<Building> buildings);
}
