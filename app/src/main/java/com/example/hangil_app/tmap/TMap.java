package com.example.hangil_app.tmap;

import static com.example.hangil_app.system.Hangil.BUILDING_COUNT;
import static com.example.hangil_app.system.Hangil.MIN_BUILDING_INDEX;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.widget.FrameLayout;

import com.example.hangil_app.R;
import com.skt.tmap.TMapData;
import com.skt.tmap.TMapGpsManager;
import com.skt.tmap.TMapPoint;
import com.skt.tmap.TMapView;
import com.skt.tmap.overlay.TMapMarkerItem;
import com.skt.tmap.overlay.TMapPolyLine;

import lombok.Setter;

public class TMap {
    public static final int INIT_ZOOM_LEVEL = 17;
    public static final int ME = 0;
    public static final int ENTRANCE = 100;
    private static final String PATH = "path";
    private static final int PATH_COLOR = 0x2285B0;
    private static final int PATH_OUTLINE_COLOR = 0x1C7094;
    private static TMap tMap; // Singleton
    private final Context context;
    private final TMapView tMapView;
    private final TMapGpsManager gpsManager;
    private final TMapData tMapData;
    @Setter
    private OnLocationChangeFirstListener onLocationChangeFirstListener;
    @Setter
    private TMapView.OnLongClickListenerCallBack onLongClickListenerCallBack;
    public boolean isLocationChanged = false;
    public boolean isGuideMode = false;
    public boolean isTmapReady = false;

    private TMap(Context context, FrameLayout container, String appKey) {
        this.context = context;
        this.tMapData = new TMapData();
        this.tMapView = new TMapView(context);

        tMapView.setSKTMapApiKey(appKey);
        container.addView(tMapView);

        gpsManager = new TMapGpsManager(context);
        gpsManager.setProvider(TMapGpsManager.PROVIDER_GPS);
        gpsManager.openGps();
        gpsManager.setProvider(TMapGpsManager.PROVIDER_NETWORK);
        gpsManager.openGps();
        gpsManager.setOnLocationChangeListener(this::onLocationChange);
    }

    // 싱글톤 패턴
    public static synchronized TMap getInstance (Context context, FrameLayout container, String appKey) {
        if (tMap == null) {
            tMap = new TMap(context, container, appKey);
        }
        return tMap;
    }

    public static TMap getTmap() {
        if (tMap == null) throw new RuntimeException("TMap 인스턴스부터 먼저 얻으삼!!");
        return tMap;
    }

    private TMapMarkerItem createMarker(int id, TMapPoint point) {
        TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
        tMapMarkerItem.setId(String.valueOf(id));
        tMapMarkerItem.setTMapPoint(point);
        return tMapMarkerItem;
    }

    public void addMarker(int id, Bitmap icon, TMapPoint point, String callOut, String subCallOut) {
        TMapMarkerItem tMapMarkerItem = createMarker(id, point);
        if (callOut != null) {
            tMapMarkerItem.setCalloutTitle(callOut);
            tMapMarkerItem.setCanShowCallout(true);
            tMapMarkerItem.setName(callOut);
        }
        if (subCallOut != null) tMapMarkerItem.setCalloutSubTitle(subCallOut);
        if (icon != null) tMapMarkerItem.setIcon(icon);
        if (id == 0) tMapMarkerItem.setPosition(0.5F, 0.5F);
        tMapView.addTMapMarkerItem(tMapMarkerItem);
    }


    public void setOnMapReadyListener(TMapView.OnMapReadyListener readyListener) {
        tMapView.setOnMapReadyListener(readyListener);
    }

    public void setZoomLevel(int zoomLevel) {
        tMapView.setZoomLevel(zoomLevel);
    }

    public void setCenter(double lat, double lon, boolean animated) {
        tMapView.setCenterPoint(lat, lon, animated);
    }

    public void setCenter(double lat, double lon) {
        tMapView.setCenterPoint(lat, lon);
    }

    public void removeMarker(int id) {
        tMapView.removeTMapMarkerItem(String.valueOf(id));
    }

    // 위치가 변할때마다 옮기기
    public void changeMeMarker(TMapPoint point) {
        removeMarker(ME);
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.me);
        addMarker(ME, icon, point, null, null);
    }
    public void onLocationChange(Location location) {
        if (!isTmapReady) return;
        if (!isLocationChanged) {
            // 처음 변했다면
            isLocationChanged = true;
            onLocationChangeFirstListener.onLocationChangeFirst(
                    new TMapPoint(
                            location.getLatitude(),
                            location.getLongitude()
                    )
            );
        }

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        tMapView.setLocationPoint(latitude, longitude);

        TMapPoint tMapPoint = new TMapPoint(latitude, longitude);
        changeMeMarker(tMapPoint);
    }

    public void onGuideMode() {
        if (isGuideMode) offGuideMode();
        isGuideMode = true;
        tMapView.setTrackingMode(true);
        tMapView.setCompassMode(true);
        tMapView.setIconVisibility(false);
    }

    public void offGuideMode() {
        isGuideMode = false;
        tMapView.setTrackingMode(false);
        tMapView.setCompassMode(false);
    }

    public void findPath(TMapPoint start, TMapPoint end, TMapData.OnFindPathDataWithTypeListener onFindPathDataWithTypeListener) {
        tMapData.findPathDataWithType(
                TMapData.TMapPathType.PEDESTRIAN_PATH,
                start,
                end,
                onFindPathDataWithTypeListener
        );
    }

    public void drawPathPolyLine(TMapPolyLine tMapPolyLine) {
        if (tMapPolyLine == null) return;
        tMapPolyLine.setID(PATH);
        tMapPolyLine.setLineColor(PATH_COLOR);
        tMapPolyLine.setOutLineColor(PATH_OUTLINE_COLOR);
        tMapView.addTMapPolyLine(tMapPolyLine);
    }

    public void erasePolyLine() {
        tMapView.removeTMapPolyLine(PATH);
    }

    public TMapPoint getLocationPoint() {
        return tMapView.getLocationPoint();
    }

    public boolean isEntraceMarker(TMapMarkerItem tMapMarkerItem) {
        return Integer.parseInt(tMapMarkerItem.getId()) >= ENTRANCE;
    }

    public boolean isBuildingMarker(TMapMarkerItem tMapMarkerItem) {
        int markerId = Integer.parseInt(tMapMarkerItem.getId());
        return MIN_BUILDING_INDEX <= markerId && markerId < MIN_BUILDING_INDEX + BUILDING_COUNT;
    }

    public void getLocAddress(TMapPoint tMapPoint,
                           TMapData.OnReverseGeocodingListener addressListener) {
        tMapData.reverseGeocoding(tMapPoint.getLatitude(), tMapPoint.getLongitude(),
                "A10", addressListener);
    }
}