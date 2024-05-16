package com.example.hangil_app.wifi;

import static com.example.hangil_app.system.Hangil.WIFI;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class WifiHelper {
    private static WifiHelper wifiHelper;
    private final WifiManager wifiManager;
    private final Context context;
    private OnWifiScanSuccessListener onWifiScanSuccessListener;

    public static WifiHelper getInstance(Context context) {
        if (wifiHelper == null) {
            return wifiHelper = new WifiHelper(context);
        } else {
            return wifiHelper;
        }
    }


    private WifiHelper(Context context) {
        this.context = context;
        this.wifiManager =
                (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        setupWifiScan();
    }

    public void scanWifi(OnWifiScanSuccessListener onWifiScanSuccessListener) {
        Log.d(WIFI, "스캔 중..");
        this.onWifiScanSuccessListener = onWifiScanSuccessListener;
        if (!wifiManager.isWifiEnabled()) throw new RuntimeException("Wifi is Disabled");
        boolean success = wifiManager.startScan();
        if (!success) {
            Log.e(WIFI, "Scanning wifi is failed");
        }
    }

    private void scanSuccess() {
        if (!checkForLocationPermission()) throw new RuntimeException("Cannot request permissions");
        onWifiScanSuccessListener.onSuccess(wifiManager.getScanResults());
    }

    private void setupWifiScan() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11 이상
            WifiManager.ScanResultsCallback scanResultsCallback = new WifiManager.ScanResultsCallback() {
                @Override
                public void onScanResultsAvailable() {
                    // 스캔 성공
                    scanSuccess();
                }
            };
        } else {
            // Android 10 이하
            IntentFilter intentFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
            BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
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

    private boolean checkForLocationPermission() {
        return ActivityCompat.checkSelfPermission(context.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}
