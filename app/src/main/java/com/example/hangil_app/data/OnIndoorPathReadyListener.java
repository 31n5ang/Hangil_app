package com.example.hangil_app.data;

import com.example.hangil_app.data.api.dto.IndoorPath;

@FunctionalInterface
public interface OnIndoorPathReadyListener {
    void onIndoorPathReady(IndoorPath indoorPath);
}
