package com.example.hangil_app.api;

import com.skt.tmap.TMapPoint;

@FunctionalInterface
public interface OnLocationChangeFirstListener {
    void onLocationChangeFirst(TMapPoint firstPoint);
}
