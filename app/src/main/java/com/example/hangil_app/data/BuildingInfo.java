package com.example.hangil_app.data;

import com.skt.tmap.TMapPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BuildingInfo {
    NONE(0, new ArrayList<>()),
    GONG2(
              1, Arrays.asList(
                      new Entrance(54, new TMapPoint(36.766598, 127.281840)),
                      new Entrance(79, new TMapPoint(36.766598, 127.281489)),
                      new Entrance(200, new TMapPoint(36.766315, 127.281847))
              )
    );

    /**
     * @id : 건물 ID
     * @entrances : 입구 좌표들
     * @nodeIds : 내부 지도 매핑 좌표들
     */
    public final int id;
    public final List<Entrance> entrances;
    BuildingInfo(int id, List<Entrance> entrances) {
        this.id = id;
        this.entrances = entrances;
    }
}



