package com.example.hangil_app.wifi;

import android.net.wifi.ScanResult;

import java.util.List;

@FunctionalInterface
public interface OnWifiScanSuccessListener {
    void onSuccess(List<ScanResult> scanResults);
}
