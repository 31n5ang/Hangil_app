package com.example.hangil_app;

import com.example.hangil_app.data.api.dto.Position;

@FunctionalInterface
public interface OnPositionGetListener {
    void onPositionGet(Position position);
}
