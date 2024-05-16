package com.example.hangil_app.search;

import androidx.annotation.NonNull;

@FunctionalInterface
public interface OnStartGuideCallback {
    void startGuide(@NonNull SearchRoomData selectedSearchRoomData);
}
