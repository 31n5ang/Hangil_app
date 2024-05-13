package com.example.hangil_app.api;

import com.example.hangil_app.api.response.Building;

import java.util.List;

@FunctionalInterface
public interface OnBuildingsReadyListener {
    void onBuildingsReady(List<Building> buildings);
}
