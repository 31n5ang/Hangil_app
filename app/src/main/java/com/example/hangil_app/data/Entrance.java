package com.example.hangil_app.data;

import com.skt.tmap.TMapPoint;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Entrance {
    private int nodeId;
    private TMapPoint tMapPoint;
}
