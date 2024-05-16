package com.example.hangil_app.data;

import com.example.hangil_app.indoor.Coord;
import com.skt.tmap.TMapPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BuildingInfo {
    NONE(0, new ArrayList<>(), 0, null),
    GONG2(
            1,
            Arrays.asList(
                      new Entrance(54, new TMapPoint(36.766598, 127.281840)),
                      new Entrance(200, new TMapPoint(36.766598, 127.281489)),
                      new Entrance(79, new TMapPoint(36.766315, 127.281847))
            ),
            4,
            new Map[] {
                    (Map) new HashMap<Integer, Coord>() {{ // 1층
                        put(212, new Coord(794, 2075));
                        put(76, new Coord(873, 2053));
                        put(77, new Coord(992, 2053));
                        put(76, new Coord(992, 2159));
                        put(207, new Coord(1172, 2159));
                        put(79, new Coord(1343, 2159));
                        put(209, new Coord(841, 2214));
                        put(73, new Coord(938, 2345));
                        put(74, new Coord(999, 2345));
                        put(75, new Coord(1061, 2345));
                        put(72, new Coord(885, 2521));
                        put(71, new Coord(1206, 2516));
                        put(68, new Coord(1056, 2723));
                        put(69, new Coord(1095, 2723));
                        put(70, new Coord(1144, 2723));
                        put(67, new Coord(1056, 2798));
                        put(66, new Coord(1095, 2798));
                        put(100, new Coord(885, 2886));
                        put(101, new Coord(1245, 2886));
                        put(65, new Coord(1056, 2983));
                        put(64, new Coord(1095, 2983));
                        put(61, new Coord(1100, 3057));
                        put(62, new Coord(1100, 3163));
                        put(63, new Coord(1056, 3163));
                        put(99, new Coord(889, 3242));
                        put(102, new Coord(1245, 3163));
                        put(59, new Coord(1324, 3057));
                        put(60, new Coord(1324, 3009));
                        put(56, new Coord(1525, 3057));
                        put(57, new Coord(1736, 3057));
                        put(58, new Coord(1736, 3099));
                        put(98, new Coord(1802, 3486));
                        put(206, new Coord(1951, 3057));
                        put(54, new Coord(2177, 3057));
                        put(55, new Coord(2177, 3225));
                        put(213, new Coord(2177, 3299));
                        put(52, new Coord(2401, 3057));
                        put(53, new Coord(2401, 3106));
                        put(50, new Coord(2629, 3057));
                        put(51, new Coord(2629, 3106));
                        put(97, new Coord(2765, 3299));
                        put(208, new Coord(2471, 3607));
                        put(48, new Coord(2943, 3057));
                        put(49, new Coord(2943, 3105));
                        put(82, new Coord(3110, 3070));
                        put(83, new Coord(3110, 3158));
                        put(46, new Coord(3264, 3074));
                        put(47, new Coord(3312, 3074));
                        put(96, new Coord(3475, 3254));
                        put(44, new Coord(3264, 2970));
                        put(45, new Coord(3220, 2970));
                        put(95, new Coord(3474, 2886));
                        put(94, new Coord(3110, 2855));
                        put(42, new Coord(3264, 2798));
                        put(43, new Coord(3316, 2798));
                        put(39, new Coord(3220, 2707));
                        put(40, new Coord(3264, 2707));
                        put(41, new Coord(3312, 2707));
                        put(93, new Coord(3483, 2610));
                        put(37, new Coord(3264, 2553));
                        put(38, new Coord(3220, 2553));
                        put(87, new Coord(3264, 2438));
                        put(35, new Coord(3264, 2315));
                        put(34, new Coord(3220, 2315));
                        put(36, new Coord(3316, 2315));
                        put(92, new Coord(3114, 2465));
                        put(28, new Coord(3264, 2245));
                        put(27, new Coord(3198, 2138));
                        put(200, new Coord(2987, 2142));
                        put(25, new Coord(3365, 2103));
                        put(29, new Coord(3466, 2191));
                        put(201, new Coord(3470, 2245));
                        put(202, new Coord(3650, 2243));
                        put(210, new Coord(3532, 2191));
                        put(203, new Coord(3592, 2103));
                        put(204, new Coord(3847, 2103));
                        put(205, new Coord(3847, 2162));
                        put(30, new Coord(4118, 2103));
                        put(31, new Coord(4118, 2044));
                        put(90, new Coord(4114, 1794));
                        put(32, new Coord(4434, 2103));
                        put(33, new Coord(4434, 2040));
                        put(91, new Coord(4439, 1789));
                        put(26, new Coord(3464, 2034));
                        put(211, new Coord(3481, 1981));
                        put(7, new Coord(3365, 1964));
                        put(20, new Coord(3365, 1828));
                        put(21, new Coord(3582, 1828));
                        put(23, new Coord(3582, 1880));
                        put(22, new Coord(3846, 1828));
                        put(24, new Coord(3837, 1867));
                        put(18, new Coord(3365, 1677));
                        put(19, new Coord(3405, 1677));
                        put(89, new Coord(3577, 1725));
                        put(16, new Coord(3365, 1514));
                        put(17, new Coord(3326, 1514));
                        put(88, new Coord(3160, 1782));
                        put(15, new Coord(3326, 1453));
                        put(12, new Coord(3370, 1453));
                        put(14, new Coord(3410, 1440));
                        put(80, new Coord(3160, 1418));
                        put(85, new Coord(3568, 1356));
                        put(10, new Coord(3365, 1325));
                        put(11, new Coord(3326, 1325));
                        put(84, new Coord(3155, 1093));
                        put(8, new Coord(3365, 1146));
                        put(9, new Coord(3418, 1146));
                        put(2, new Coord(3365, 988));
                        put(1, new Coord(3365, 786));
                        put(3, new Coord(3436, 988));
                        put(5, new Coord(3497, 988));
                        put(4, new Coord(3436, 918));
                        put(13, new Coord(3647, 966));
                    }},
                    (Map) new HashMap<>() // 2층
                            .put(5, new Coord(3, 5)),
            }
    );

    /**
     * @id : 건물 ID
     * @entrances : 입구 좌표들
     * @floorCount : 건물 높이
     */
    public final int id;
    public final List<Entrance> entrances;
    public final int floorCount;
    public final Map<Integer, Coord>[] coordById;
    BuildingInfo(int id, List<Entrance> entrances, int floorCount,
                 Map<Integer, Coord>[] coordById) {
        this.id = id;
        this.entrances = entrances;
        this.floorCount = floorCount;
        this.coordById = coordById;
    }
}



