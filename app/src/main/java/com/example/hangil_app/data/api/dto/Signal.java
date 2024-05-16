package com.example.hangil_app.data.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Signal {
    private String ssid;
    private String mac;
    private int rssi;
}
