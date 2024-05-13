package com.example.hangil_app.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import com.example.hangil_app.system.Hangil;

import java.util.ArrayList;
import java.util.List;

public class WifiHelper {
    private WifiManager wifiManager;
    private List<String> scanResults = new ArrayList<>();
    private BroadcastReceiver wifiScanReceiver;
    private WifiManager.ScanResultsCallback scanResultsCallback;
    private final Context context;

    public WifiHelper(Context context) {
        this.context = context;
        this.wifiManager =
                (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        setupWifiScan();
    }

    public void scanWifi() {
        Log.d(Hangil.WIFI, "wifi 스캔 중!!");
        if (!wifiManager.isWifiEnabled()) {
            Log.e(Hangil.WIFI, "wifi 꺼져있다");
            return;
        }
        boolean success = wifiManager.startScan();
        if (!success) {
            Log.e(Hangil.WIFI, "wifi 스캔 실패요");
        }
    }

    private void scanSuccess() {
        Log.d(Hangil.WIFI, "스캔 성공~~");
    }

    private void setupWifiScan() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11 이상
            scanResultsCallback = new WifiManager.ScanResultsCallback() {
                @Override
                public void onScanResultsAvailable() {
                    // 스캔 성공
                    scanSuccess();
                }
            };
        } else {
            // Android 10 이하
            IntentFilter intentFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
            wifiScanReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    boolean isSuccess = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED,
                            false);
                    if (isSuccess) {
                        // 스캔 성공
                        scanSuccess();
                    }
                }
            };
            context.getApplicationContext().registerReceiver(wifiScanReceiver, intentFilter);
        }
    }
}
