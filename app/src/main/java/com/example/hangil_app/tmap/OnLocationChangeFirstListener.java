package com.example.hangil_app.tmap;

import com.skt.tmap.TMapPoint;

@FunctionalInterface
public interface OnLocationChangeFirstListener {
    void onLocationChangeFirst(TMapPoint firstPoint);
}
