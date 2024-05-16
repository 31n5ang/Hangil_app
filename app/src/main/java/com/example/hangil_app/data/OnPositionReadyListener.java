package com.example.hangil_app.data;

import com.example.hangil_app.data.api.dto.Position;

@FunctionalInterface
public interface OnPositionReadyListener {
    void onPositionReady(Position position);
}
