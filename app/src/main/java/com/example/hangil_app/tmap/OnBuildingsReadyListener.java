package com.example.hangil_app.tmap;

import com.example.hangil_app.tmap.api.response.Building;

import java.util.List;

@FunctionalInterface
public interface OnBuildingsReadyListener {
    void onBuildingsReady(List<Building> buildings);
}
