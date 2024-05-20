package com.example.hangil_app.search;

@FunctionalInterface
public interface OnSelectRoomCallback {
    void onSelectRoom(boolean isStartRoom,
                      SearchRoomData selectedSearchRoomData);
}
