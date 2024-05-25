package com.example.hangil_app.data;

import com.example.hangil_app.indoor.Coord;
import com.skt.tmap.TMapPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BuildingInfo {
    NONE(0, "NONE", new ArrayList<>(), 0, null),
    GONG2(
            1,
            "공학 2관",
            Arrays.asList(
                    // 건물 입구 들의 좌표를 나타냄
                      new Entrance(35, 54, new TMapPoint(36.766598, 127.281840)),
                      new Entrance(83, 200, new TMapPoint(36.766598, 127.281489)),
                      new Entrance(84, 79, new TMapPoint(36.766315, 127.281847))
            ),
            4, // 층 수
            new Map[] {
                    /**
                     * 사용방법
                     * [DataManager.F1~4].get(지도의 노드번호)
                    */
                    null, // NONE
                    (Map) new HashMap<Integer, Coord>() {{ // 1층
                        put(1, new Coord(3365, 786));
                        put(2, new Coord(3365, 988));
                        put(3, new Coord(3436, 988));
                        put(4, new Coord(3436, 918));
                        put(5, new Coord(3497, 988));
                        put(7, new Coord(3365, 1964));
                        put(8, new Coord(3365, 1146));
                        put(9, new Coord(3418, 1146));
                        put(10, new Coord(3365, 1325));
                        put(11, new Coord(3326, 1325));
                        put(12, new Coord(3370, 1453));
                        put(13, new Coord(3647, 966));
                        put(14, new Coord(3410, 1440));
                        put(15, new Coord(3326, 1453));
                        put(16, new Coord(3365, 1514));
                        put(17, new Coord(3326, 1514));
                        put(18, new Coord(3365, 1677));
                        put(19, new Coord(3405, 1677));
                        put(20, new Coord(3365, 1828));
                        put(21, new Coord(3582, 1828));
                        put(22, new Coord(3846, 1828));
                        put(23, new Coord(3582, 1880));
                        put(24, new Coord(3837, 1867));
                        put(25, new Coord(3365, 2103));
                        put(26, new Coord(3464, 2034));
                        put(27, new Coord(3198, 2138));
                        put(28, new Coord(3264, 2245));
                        put(29, new Coord(3466, 2191));
                        put(30, new Coord(4118, 2103));
                        put(31, new Coord(4118, 2044));
                        put(32, new Coord(4434, 2103));
                        put(33, new Coord(4434, 2040));
                        put(34, new Coord(3220, 2315));
                        put(35, new Coord(3264, 2315));
                        put(36, new Coord(3316, 2315));
                        put(37, new Coord(3264, 2553));
                        put(38, new Coord(3220, 2553));
                        put(39, new Coord(3220, 2707));
                        put(40, new Coord(3264, 2707));
                        put(41, new Coord(3312, 2707));
                        put(42, new Coord(3264, 2798));
                        put(43, new Coord(3316, 2798));
                        put(44, new Coord(3264, 2970));
                        put(45, new Coord(3220, 2970));
                        put(46, new Coord(3264, 3074));
                        put(47, new Coord(3312, 3074));
                        put(48, new Coord(2943, 3057));
                        put(49, new Coord(2943, 3105));
                        put(50, new Coord(2629, 3057));
                        put(51, new Coord(2629, 3106));
                        put(52, new Coord(2401, 3057));
                        put(53, new Coord(2401, 3106));
                        put(54, new Coord(2177, 3057));
                        put(55, new Coord(2177, 3225));
                        put(56, new Coord(1525, 3057));
                        put(57, new Coord(1736, 3057));
                        put(58, new Coord(1736, 3099));
                        put(59, new Coord(1324, 3057));
                        put(60, new Coord(1324, 3009));
                        put(61, new Coord(1100, 3057));
                        put(62, new Coord(1100, 3163));
                        put(63, new Coord(1056, 3163));
                        put(64, new Coord(1095, 2983));
                        put(65, new Coord(1056, 2983));
                        put(66, new Coord(1095, 2798));
                        put(67, new Coord(1056, 2798));
                        put(68, new Coord(1056, 2723));
                        put(69, new Coord(1095, 2723));
                        put(70, new Coord(1144, 2723));
                        put(71, new Coord(1206, 2516));
                        put(72, new Coord(885, 2521));
                        put(73, new Coord(938, 2345));
                        put(74, new Coord(999, 2345));
                        put(75, new Coord(1061, 2345));
                        put(76, new Coord(992, 2159));
                        put(77, new Coord(992, 2053));
                        put(78, new Coord(881, 2055));
                        put(79, new Coord(1343, 2159));
                        put(82, new Coord(3110, 3070));
                        put(83, new Coord(3110, 3158));
                        put(84, new Coord(3155, 1093));
                        put(85, new Coord(3568, 1356));
                        put(86, new Coord(3160, 1418));
                        put(87, new Coord(3264, 2438));
                        put(88, new Coord(3160, 1782));
                        put(89, new Coord(3577, 1725));
                        put(90, new Coord(4114, 1794));
                        put(91, new Coord(4439, 1789));
                        put(92, new Coord(3114, 2465));
                        put(93, new Coord(3483, 2610));
                        put(94, new Coord(3110, 2855));
                        put(95, new Coord(3474, 2886));
                        put(96, new Coord(3475, 3254));
                        put(97, new Coord(2765, 3299));
                        put(98, new Coord(1802, 3486));
                        put(99, new Coord(889, 3242));
                        put(100, new Coord(885, 2886));
                        put(101, new Coord(1245, 2886));
                        put(102, new Coord(1245, 3163));
                        put(200, new Coord(2987, 2142));
                        put(201, new Coord(3470, 2245));
                        put(202, new Coord(3650, 2243));
                        put(203, new Coord(3592, 2103));
                        put(204, new Coord(3847, 2103));
                        put(205, new Coord(3847, 2162));
                        put(206, new Coord(1951, 3057));
                        put(207, new Coord(1172, 2159));
                        put(208, new Coord(2471, 3607));
                        put(209, new Coord(841, 2214));
                        put(211, new Coord(3481, 1981));
                        put(213, new Coord(2177, 3299));
                        put(500, new Coord(934, 2050));
                        put(501, new Coord(1090, 2146));
                        put(502, new Coord(1251, 2150));
                        put(503, new Coord(986, 2230));
                        put(504, new Coord(1094, 2880));
                        put(505, new Coord(1199, 3056));
                        put(506, new Coord(1171, 3096));
                        put(507, new Coord(1307, 3112));
                        put(508, new Coord(1418, 3055));
                        put(509, new Coord(1617, 3061));
                        put(510, new Coord(1836, 3057));
                        put(511, new Coord(2049, 3061));
                        put(512, new Coord(2174, 3141));
                        put(513, new Coord(2288, 3057));
                        put(514, new Coord(2501, 3049));
                        put(515, new Coord(2728, 3051));
                        put(516, new Coord(2824, 3051));
                        put(517, new Coord(3017, 3062));
                        put(518, new Coord(3185, 3066));
                        put(519, new Coord(3257, 2882));
                        put(520, new Coord(3260, 2622));
                        put(521, new Coord(3260, 2493));
                        put(522, new Coord(3264, 2365));
                        put(523, new Coord(3092, 2124));
                        put(524, new Coord(3275, 2102));
                        put(525, new Coord(3331, 2167));
                        put(526, new Coord(3359, 2231));
                        put(527, new Coord(3556, 2239));
                        put(528, new Coord(3487, 2098));
                        put(529, new Coord(3708, 2102));
                        put(530, new Coord(4206, 2098));
                        put(531, new Coord(4334, 2102));
                        put(532, new Coord(3367, 1894));
                        put(533, new Coord(3467, 1830));
                        put(534, new Coord(3708, 1830));
                        put(535, new Coord(3363, 1745));
                        put(536, new Coord(3363, 1589));
                        put(537, new Coord(3363, 1376));
                        put(538, new Coord(3367, 1228));
                        put(539, new Coord(3363, 1064));
                        put(540, new Coord(3367, 879));
                        put(541, new Coord(3974, 2101));
                        put(600, new Coord(792, 2034));
                        put(601, new Coord(741, 2034));
                        put(602, new Coord(2179, 3323));
                        put(603, new Coord(3607, 2186));
                        put(604, new Coord(3509, 884));
                        put(605, new Coord(3602, 884));
                    }},
                    (Map) new HashMap<Integer, Coord>() {{ // 2층
                        put(1, new Coord(3367, 847));
                        put(2, new Coord(3367, 883));
                        put(3, new Coord(3481, 855));
                        put(4, new Coord(3326, 847));
                        put(5, new Coord(3411, 883));
                        put(6, new Coord(3367, 953));
                        put(7, new Coord(3326, 953));
                        put(8, new Coord(3407, 953));
                        put(9, new Coord(3367, 1079));
                        put(10, new Coord(3326, 1079));
                        put(11, new Coord(3407, 1079));
                        put(12, new Coord(3326, 1194));
                        put(13, new Coord(3367, 1194));
                        put(14, new Coord(3407, 1194));
                        put(15, new Coord(3326, 1324));
                        put(16, new Coord(3367, 1324));
                        put(17, new Coord(3407, 1324));
                        put(18, new Coord(3326, 1443));
                        put(19, new Coord(3367, 1443));
                        put(20, new Coord(3407, 1443));
                        put(21, new Coord(3326, 1565));
                        put(22, new Coord(3367, 1565));
                        put(23, new Coord(3407, 1569));
                        put(24, new Coord(3326, 1696));
                        put(25, new Coord(3367, 1696));
                        put(26, new Coord(3407, 1692));
                        put(27, new Coord(3326, 1970));
                        put(28, new Coord(3367, 1970));
                        put(29, new Coord(3572, 1831));
                        put(30, new Coord(3572, 1876));
                        put(31, new Coord(3842, 1831));
                        put(32, new Coord(3842, 1868));
                        put(33, new Coord(3367, 2158));
                        put(34, new Coord(3490, 2158));
                        put(35, new Coord(3470, 2035));
                        put(36, new Coord(3249, 2150));
                        put(37, new Coord(3231, 2370));
                        put(38, new Coord(3278, 2370));
                        put(39, new Coord(3310, 2366));
                        put(40, new Coord(3231, 2419));
                        put(41, new Coord(3278, 2419));
                        put(42, new Coord(3315, 2427));
                        put(43, new Coord(3278, 2546));
                        put(44, new Coord(3315, 2546));
                        put(45, new Coord(3231, 2619));
                        put(46, new Coord(3280, 2619));
                        put(47, new Coord(3231, 2664));
                        put(48, new Coord(3280, 2668));
                        put(49, new Coord(3309, 2681));
                        put(50, new Coord(3309, 2738));
                        put(51, new Coord(3280, 2738));
                        put(52, new Coord(3231, 2860));
                        put(53, new Coord(3317, 2860));
                        put(54, new Coord(3280, 2860));
                        put(55, new Coord(3317, 2911));
                        put(56, new Coord(3280, 2911));
                        put(57, new Coord(3231, 2911));
                        put(58, new Coord(3112, 3065));
                        put(59, new Coord(3276, 3065));
                        put(60, new Coord(3112, 3156));
                        put(61, new Coord(2914, 3098));
                        put(62, new Coord(2914, 3065));
                        put(63, new Coord(2579, 3065));
                        put(64, new Coord(2579, 3106));
                        put(65, new Coord(2397, 3106));
                        put(66, new Coord(2397, 3065));
                        put(67, new Coord(2183, 3172));
                        put(68, new Coord(1970, 3103));
                        put(69, new Coord(1970, 3065));
                        put(70, new Coord(1447, 3106));
                        put(71, new Coord(1447, 3065));
                        put(72, new Coord(1254, 3163));
                        put(73, new Coord(1254, 3065));
                        put(74, new Coord(1094, 3065));
                        put(75, new Coord(1094, 3012));
                        put(76, new Coord(999, 3179));
                        put(77, new Coord(999, 3155));
                        put(78, new Coord(954, 3155));
                        put(79, new Coord(954, 3106));
                        put(80, new Coord(1003, 3106));
                        put(81, new Coord(1003, 3073));
                        put(82, new Coord(954, 2991));
                        put(83, new Coord(1003, 2991));
                        put(84, new Coord(1003, 2861));
                        put(85, new Coord(954, 2861));
                        put(86, new Coord(954, 2730));
                        put(87, new Coord(999, 2730));
                        put(88, new Coord(1003, 2665));
                        put(89, new Coord(1048, 2665));
                        put(90, new Coord(1003, 2615));
                        put(91, new Coord(1040, 2615));
                        put(92, new Coord(1003, 2534));
                        put(93, new Coord(954, 2534));
                        put(94, new Coord(954, 2419));
                        put(95, new Coord(1003, 2419));
                        put(96, new Coord(1003, 2375));
                        put(97, new Coord(954, 2375));
                        put(98, new Coord(1003, 2326));
                        put(99, new Coord(1040, 2326));
                        put(100, new Coord(1003, 2171));
                        put(101, new Coord(954, 2171));
                        put(102, new Coord(938, 2087));
                        put(103, new Coord(1003, 2087));
                        put(104, new Coord(835, 2208));
                        put(105, new Coord(835, 2334));
                        put(106, new Coord(831, 2456));
                        put(107, new Coord(843, 2591));
                        put(108, new Coord(831, 2705));
                        put(109, new Coord(831, 2836));
                        put(110, new Coord(839, 2959));
                        put(111, new Coord(835, 3073));
                        put(112, new Coord(848, 3195));
                        put(113, new Coord(880, 3375));
                        put(114, new Coord(1208, 2456));
                        put(115, new Coord(1175, 2722));
                        put(116, new Coord(1195, 2914));
                        put(117, new Coord(1504, 3221));
                        put(118, new Coord(1692, 3323));
                        put(119, new Coord(2442, 3315));
                        put(120, new Coord(2608, 3311));
                        put(121, new Coord(2829, 3307));
                        put(122, new Coord(3481, 2955));
                        put(123, new Coord(3117, 2955));
                        put(124, new Coord(3121, 2832));
                        put(125, new Coord(3485, 2832));
                        put(126, new Coord(3485, 2709));
                        put(127, new Coord(3117, 2705));
                        put(128, new Coord(3119, 2582));
                        put(129, new Coord(3483, 2582));
                        put(130, new Coord(3487, 2460));
                        put(131, new Coord(3115, 2456));
                        put(132, new Coord(3122, 2338));
                        put(133, new Coord(3482, 2329));
                        put(134, new Coord(3122, 2146));
                        put(135, new Coord(3159, 1913));
                        put(136, new Coord(3157, 1721));
                        put(137, new Coord(3571, 1712));
                        put(138, new Coord(3575, 1606));
                        put(139, new Coord(3161, 1610));
                        put(140, new Coord(3161, 1476));
                        put(141, new Coord(3575, 1488));
                        put(142, new Coord(3571, 1361));
                        put(143, new Coord(3166, 1361));
                        put(144, new Coord(3157, 1230));
                        put(145, new Coord(3571, 1239));
                        put(146, new Coord(3571, 1112));
                        put(147, new Coord(3161, 1120));
                        put(148, new Coord(3170, 998));
                        put(149, new Coord(3566, 965));
                        put(150, new Coord(3157, 871));
                        put(151, new Coord(3367, 1831));
                        put(152, new Coord(3695, 1831));
                        put(153, new Coord(3690, 2109));
                        put(154, new Coord(4114, 2109));
                        put(155, new Coord(4437, 2109));
                        put(156, new Coord(4114, 2044));
                        put(157, new Coord(4437, 2040));
                        put(158, new Coord(4110, 1774));
                        put(159, new Coord(4445, 1774));
                        put(200, new Coord(1692, 3065));
                        put(201, new Coord(2183, 3065));
                        put(202, new Coord(2747, 3065));
                        put(203, new Coord(3695, 1970));
                        put(204, new Coord(3892, 2109));
                        put(205, new Coord(3572, 2162));
                        put(206, new Coord(3478, 1978));
                        put(207, new Coord(2183, 3286));
                        put(499, new Coord(857, 2087));
                        put(500, new Coord(1000, 2248));
                        put(501, new Coord(1004, 2481));
                        put(502, new Coord(996, 2796));
                        put(503, new Coord(998, 2919));
                        put(504, new Coord(1171, 3062));
                        put(505, new Coord(1349, 3054));
                        put(506, new Coord(1558, 3066));
                        put(507, new Coord(1769, 3064));
                        put(508, new Coord(1867, 3064));
                        put(509, new Coord(2072, 3068));
                        put(510, new Coord(2272, 3063));
                        put(511, new Coord(2481, 3063));
                        put(512, new Coord(2658, 3063));
                        put(513, new Coord(2834, 3067));
                        put(514, new Coord(3011, 3062));
                        put(515, new Coord(3191, 3062));
                        put(516, new Coord(3272, 2989));
                        put(517, new Coord(3277, 2788));
                        put(518, new Coord(3278, 2470));
                        put(519, new Coord(3275, 2247));
                        put(520, new Coord(3426, 2153));
                        put(521, new Coord(3422, 2079));
                        put(522, new Coord(3365, 2051));
                        put(523, new Coord(3369, 1899));
                        put(524, new Coord(3467, 1829));
                        put(525, new Coord(3627, 1825));
                        put(526, new Coord(3758, 1825));
                        put(527, new Coord(3689, 1891));
                        put(528, new Coord(3685, 2042));
                        put(529, new Coord(3787, 2108));
                        put(530, new Coord(3992, 2111));
                        put(531, new Coord(4197, 2110));
                        put(532, new Coord(4336, 2110));
                        put(533, new Coord(3368, 1757));
                        put(534, new Coord(3368, 1622));
                        put(535, new Coord(3372, 1507));
                        put(536, new Coord(3368, 1372));
                        put(537, new Coord(3368, 1253));
                        put(538, new Coord(3368, 1138));
                        put(539, new Coord(3368, 1011));
                        put(600, new Coord(818, 2114));
                        put(601, new Coord(730, 2114));
                        put(602, new Coord(730, 2039));
                        put(603, new Coord(818, 2039));
                        put(604, new Coord(2064, 3152));
                        put(605, new Coord(2064, 3264));
                        put(606, new Coord(2064, 3334));
                        put(607, new Coord(2176, 3334));
                        put(608, new Coord(2289, 3334));
                        put(609, new Coord(2289, 3264));
                        put(610, new Coord(2289, 3152));
                        put(611, new Coord(3565, 2191));
                        put(612, new Coord(3631, 2191));
                        put(613, new Coord(3631, 2116));
                        put(614, new Coord(3565, 2116));
                        put(615, new Coord(3520, 878));
                        put(616, new Coord(3604, 878));
                        put(617, new Coord(3431, 818));
                        put(618, new Coord(3520, 818));
                        put(619, new Coord(3604, 813));
                    }},
                    (Map) new HashMap<Integer, Coord>() {{
                        // 3층
                        put(1, new Coord(867, 2078));
                        put(2, new Coord(934, 2065));
                        put(3, new Coord(997, 2065));
                        put(4, new Coord(997, 2120));
                        put(5, new Coord(1094, 2065));
                        put(6, new Coord(1141, 2065));
                        put(7, new Coord(1094, 2419));
                        put(8, new Coord(1149, 2419));
                        put(9, new Coord(1094, 2814));
                        put(10, new Coord(1052, 2814));
                        put(11, new Coord(1094, 3067));
                        put(12, new Coord(1052, 3164));
                        put(13, new Coord(1250, 3160));
                        put(14, new Coord(1431, 3063));
                        put(15, new Coord(1431, 3109));
                        put(16, new Coord(1707, 3063));
                        put(17, new Coord(1707, 3106));
                        put(18, new Coord(1969, 3063));
                        put(19, new Coord(1969, 3110));
                        put(20, new Coord(2179, 3063));
                        put(21, new Coord(2179, 3224));
                        put(22, new Coord(2400, 3063));
                        put(23, new Coord(2400, 3110));
                        put(24, new Coord(2610, 3063));
                        put(25, new Coord(2610, 3110));
                        put(26, new Coord(2934, 3063));
                        put(27, new Coord(2934, 3106));
                        put(28, new Coord(3111, 3063));
                        put(29, new Coord(3111, 3157));
                        put(30, new Coord(3261, 3207));
                        put(31, new Coord(3311, 3207));
                        put(32, new Coord(3261, 3063));
                        put(33, new Coord(3311, 3063));
                        put(34, new Coord(3261, 2972));
                        put(35, new Coord(3219, 2972));
                        put(36, new Coord(3315, 2972));
                        put(37, new Coord(3261, 2803));
                        put(38, new Coord(3315, 2803));
                        put(39, new Coord(3261, 2736));
                        put(40, new Coord(3315, 2736));
                        put(41, new Coord(3261, 2665));
                        put(42, new Coord(3219, 2665));
                        put(43, new Coord(3261, 2614));
                        put(44, new Coord(3214, 2614));
                        put(45, new Coord(3261, 2555));
                        put(46, new Coord(3307, 2555));
                        put(47, new Coord(3261, 2479));
                        put(48, new Coord(3307, 2479));
                        put(49, new Coord(3214, 2303));
                        put(50, new Coord(3315, 2303));
                        put(51, new Coord(3261, 2269));
                        put(52, new Coord(3261, 2153));
                        put(53, new Coord(3484, 2153));
                        put(54, new Coord(3364, 2041));
                        put(55, new Coord(3461, 2037));
                        put(56, new Coord(3364, 1805));
                        put(57, new Coord(3326, 1814));
                        put(58, new Coord(3612, 1835));
                        put(59, new Coord(3612, 1873));
                        put(60, new Coord(3839, 1835));
                        put(61, new Coord(3839, 1868));
                        put(62, new Coord(3806, 1915));
                        put(63, new Coord(3869, 1906));
                        put(64, new Coord(3945, 1835));
                        put(65, new Coord(3367, 1691));
                        put(66, new Coord(3321, 1691));
                        put(67, new Coord(3413, 1691));
                        put(68, new Coord(3371, 1565));
                        put(69, new Coord(3312, 1565));
                        put(70, new Coord(3418, 1565));
                        put(71, new Coord(3367, 1435));
                        put(72, new Coord(3409, 1435));
                        put(73, new Coord(3312, 1435));
                        put(74, new Coord(3367, 1321));
                        put(75, new Coord(3418, 1321));
                        put(76, new Coord(3316, 1321));
                        put(77, new Coord(3367, 1199));
                        put(78, new Coord(3413, 1199));
                        put(79, new Coord(3329, 1199));
                        put(80, new Coord(3367, 1077));
                        put(81, new Coord(3413, 1077));
                        put(82, new Coord(3325, 1077));
                        put(83, new Coord(3367, 951));
                        put(84, new Coord(3418, 955));
                        put(85, new Coord(3325, 951));
                        put(86, new Coord(3418, 888));
                        put(87, new Coord(3367, 829));
                        put(88, new Coord(3312, 829));
                        put(89, new Coord(3472, 850));
                        put(90, new Coord(3157, 875));
                        put(91, new Coord(3152, 997));
                        put(92, new Coord(3152, 1111));
                        put(93, new Coord(3152, 1245));
                        put(94, new Coord(3152, 1367));
                        put(95, new Coord(3152, 1489));
                        put(96, new Coord(3144, 1603));
                        put(97, new Coord(3156, 1717));
                        put(98, new Coord(3641, 959));
                        put(99, new Coord(3599, 1115));
                        put(100, new Coord(3586, 1229));
                        put(102, new Coord(3582, 1489));
                        put(103, new Coord(3590, 1603));
                        put(104, new Coord(3582, 1717));
                        put(105, new Coord(3924, 1940));
                        put(106, new Coord(4288, 1767));
                        put(107, new Coord(3591, 1953));
                        put(108, new Coord(3145, 1898));
                        put(109, new Coord(3109, 2437));
                        put(110, new Coord(3105, 2824));
                        put(111, new Coord(3492, 2383));
                        put(112, new Coord(3471, 2627));
                        put(113, new Coord(3480, 2875));
                        put(114, new Coord(3480, 3250));
                        put(116, new Coord(2450, 3299));
                        put(119, new Coord(871, 3256));
                        put(120, new Coord(871, 2819));
                        put(121, new Coord(1254, 2250));
                        put(122, new Coord(875, 2335));
                        put(200, new Coord(1094, 2246));
                        put(201, new Coord(1094, 2600));
                        put(202, new Coord(1254, 3067));
                        put(203, new Coord(2179, 3321));
                        put(204, new Coord(2779, 3063));
                        put(205, new Coord(3574, 2153));
                        put(206, new Coord(3477, 1978));
                        put(207, new Coord(1259, 2549));
                        put(208, new Coord(1141, 2600));
                        put(209, new Coord(1250, 2814));
                        put(210, new Coord(1094, 2709));
                        put(211, new Coord(1141, 2709));
                        put(212, new Coord(1094, 2983));
                        put(213, new Coord(1141, 2983));
                        put(500, new Coord(1096, 2146));
                        put(501, new Coord(1092, 2322));
                        put(502, new Coord(1096, 2503));
                        put(503, new Coord(1092, 2896));
                        put(504, new Coord(1165, 3064));
                        put(505, new Coord(1333, 3064));
                        put(506, new Coord(1562, 3059));
                        put(507, new Coord(1828, 3068));
                        put(508, new Coord(2075, 3058));
                        put(509, new Coord(2280, 3062));
                        put(510, new Coord(2491, 3060));
                        put(511, new Coord(2693, 3065));
                        put(512, new Coord(2853, 3065));
                        put(513, new Coord(3013, 3057));
                        put(514, new Coord(3181, 3061));
                        put(515, new Coord(3259, 3118));
                        put(516, new Coord(3263, 2867));
                        put(517, new Coord(3267, 2372));
                        put(518, new Coord(3374, 2154));
                        put(519, new Coord(3374, 2089));
                        put(520, new Coord(3313, 2085));
                        put(521, new Coord(3366, 1912));
                        put(522, new Coord(3460, 1822));
                        put(523, new Coord(3718, 1839));
                        put(524, new Coord(3366, 1738));
                        put(525, new Coord(3366, 1624));
                        put(526, new Coord(3366, 1501));
                        put(527, new Coord(3362, 1370));
                        put(528, new Coord(3366, 1259));
                        put(529, new Coord(3366, 1132));
                        put(530, new Coord(3369, 1016));
                        put(531, new Coord(3361, 885));
                        put(600, new Coord(815, 2122));
                        put(601, new Coord(735, 2122));
                        put(602, new Coord(735, 2029));
                        put(603, new Coord(815, 2033));
                        put(604, new Coord(2068, 3216));
                        put(605, new Coord(2068, 3291));
                        put(606, new Coord(2068, 3343));
                        put(607, new Coord(2185, 3343));
                        put(608, new Coord(2297, 3343));
                        put(609, new Coord(2297, 3291));
                        put(610, new Coord(2297, 3216));
                        put(611, new Coord(3565, 2191));
                        put(612, new Coord(3631, 2191));
                        put(613, new Coord(3631, 2116));
                        put(614, new Coord(3565, 2116));
                        put(615, new Coord(3520, 878));
                        put(616, new Coord(3604, 878));
                        put(617, new Coord(3431, 818));
                        put(618, new Coord(3520, 818));
                        put(619, new Coord(3604, 813));
                    }},
                    (Map) new HashMap<Integer, Coord>() {{
                        // 4층
                        put(1, new Coord(933, 2066));
                        put(2, new Coord(888, 2083));
                        put(3, new Coord(1106, 2070));
                        put(4, new Coord(1106, 2198));
                        put(5, new Coord(1053, 2198));
                        put(6, new Coord(1106, 2286));
                        put(7, new Coord(1139, 2286));
                        put(8, new Coord(1106, 2491));
                        put(9, new Coord(1143, 2491));
                        put(10, new Coord(1106, 2619));
                        put(11, new Coord(1052, 2619));
                        put(12, new Coord(1106, 2648));
                        put(13, new Coord(1147, 2648));
                        put(14, new Coord(1106, 2817));
                        put(15, new Coord(1139, 2817));
                        put(16, new Coord(1106, 2981));
                        put(17, new Coord(1147, 2981));
                        put(18, new Coord(1093, 3047));
                        put(19, new Coord(1048, 3047));
                        put(20, new Coord(1250, 3047));
                        put(21, new Coord(1250, 3150));
                        put(22, new Coord(1374, 3047));
                        put(23, new Coord(1464, 3047));
                        put(24, new Coord(1464, 3101));
                        put(25, new Coord(2181, 3047));
                        put(26, new Coord(2181, 3228));
                        put(27, new Coord(2635, 3047));
                        put(28, new Coord(2635, 3108));
                        put(29, new Coord(2676, 3047));
                        put(30, new Coord(2676, 3112));
                        put(31, new Coord(2990, 3047));
                        put(32, new Coord(3111, 3047));
                        put(33, new Coord(3111, 3162));
                        put(34, new Coord(3317, 3047));
                        put(35, new Coord(3226, 2969));
                        put(36, new Coord(3317, 2969));
                        put(37, new Coord(3312, 2799));
                        put(38, new Coord(3308, 2729));
                        put(39, new Coord(3218, 2540));
                        put(40, new Coord(3308, 2548));
                        put(41, new Coord(3312, 2478));
                        put(42, new Coord(3226, 2326));
                        put(43, new Coord(3317, 2301));
                        put(44, new Coord(3267, 2260));
                        put(45, new Coord(3267, 2153));
                        put(46, new Coord(3490, 2153));
                        put(47, new Coord(3465, 2030));
                        put(48, new Coord(3370, 1931));
                        put(49, new Coord(3321, 1931));
                        put(50, new Coord(3370, 1807));
                        put(51, new Coord(3321, 1811));
                        put(52, new Coord(3605, 1832));
                        put(53, new Coord(3605, 1877));
                        put(54, new Coord(3836, 1832));
                        put(55, new Coord(3836, 1877));
                        put(56, new Coord(3370, 1684));
                        put(57, new Coord(3325, 1684));
                        put(58, new Coord(3411, 1684));
                        put(59, new Coord(3370, 1581));
                        put(60, new Coord(3325, 1581));
                        put(61, new Coord(3411, 1581));
                        put(62, new Coord(3370, 1440));
                        put(63, new Coord(3325, 1440));
                        put(64, new Coord(3412, 1440));
                        put(65, new Coord(3370, 1325));
                        put(66, new Coord(3325, 1325));
                        put(67, new Coord(3412, 1325));
                        put(68, new Coord(3370, 1192));
                        put(69, new Coord(3325, 1192));
                        put(70, new Coord(3412, 1192));
                        put(71, new Coord(3370, 1078));
                        put(72, new Coord(3325, 1078));
                        put(73, new Coord(3412, 1078));
                        put(74, new Coord(3370, 953));
                        put(75, new Coord(3325, 953));
                        put(76, new Coord(3412, 953));
                        put(77, new Coord(3370, 891));
                        put(78, new Coord(3412, 891));
                        put(79, new Coord(3370, 850));
                        put(80, new Coord(3325, 850));
                        put(81, new Coord(3465, 854));
                        put(82, new Coord(3135, 862));
                        put(83, new Coord(3135, 982));
                        put(84, new Coord(3135, 1111));
                        put(85, new Coord(3572, 965));
                        put(86, new Coord(3572, 1119));
                        put(88, new Coord(3572, 1366));
                        put(89, new Coord(3135, 1226));
                        put(90, new Coord(3135, 1358));
                        put(91, new Coord(3135, 1473));
                        put(92, new Coord(3568, 1477));
                        put(93, new Coord(3139, 1606));
                        put(94, new Coord(3136, 1725));
                        put(95, new Coord(3131, 1836));
                        put(96, new Coord(3139, 1976));
                        put(97, new Coord(3568, 1613));
                        put(98, new Coord(3572, 1728));
                        put(99, new Coord(3597, 1943));
                        put(100, new Coord(3473, 2330));
                        put(101, new Coord(3477, 2458));
                        put(103, new Coord(3477, 2709));
                        put(104, new Coord(3481, 2832));
                        put(105, new Coord(3473, 2948));
                        put(106, new Coord(3486, 3253));
                        put(107, new Coord(2825, 3302));
                        put(108, new Coord(2499, 3298));
                        put(109, new Coord(1702, 3290));
                        put(110, new Coord(875, 3257));
                        put(111, new Coord(1263, 2919));
                        put(112, new Coord(867, 2808));
                        put(113, new Coord(1267, 2763));
                        put(114, new Coord(1267, 2598));
                        put(115, new Coord(871, 2354));
                        put(116, new Coord(1263, 2240));
                        put(117, new Coord(3094, 2392));
                        put(118, new Coord(3102, 2754));
                        put(119, new Coord(3267, 2326));
                        put(120, new Coord(3267, 2478));
                        put(121, new Coord(3267, 2548));
                        put(122, new Coord(3267, 2729));
                        put(123, new Coord(3267, 2799));
                        put(124, new Coord(3267, 2969));
                        put(125, new Coord(3267, 3047));
                        put(200, new Coord(1822, 3047));
                        put(201, new Coord(2404, 3047));
                        put(202, new Coord(2185, 3314));
                        put(203, new Coord(3601, 2157));
                        put(204, new Coord(1093, 3339));
                        put(205, new Coord(3490, 1939));
                        put(500, new Coord(993, 2072));
                        put(501, new Coord(1048, 2068));
                        put(502, new Coord(1104, 2128));
                        put(503, new Coord(1099, 2354));
                        put(504, new Coord(1104, 2410));
                        put(505, new Coord(1099, 2567));
                        put(506, new Coord(1099, 2724));
                        put(507, new Coord(1099, 2899));
                        put(508, new Coord(1164, 3057));
                        put(509, new Coord(1307, 3057));
                        put(510, new Coord(1567, 3056));
                        put(511, new Coord(1710, 3056));
                        put(512, new Coord(1923, 3056));
                        put(513, new Coord(2090, 3056));
                        put(514, new Coord(2183, 3140));
                        put(515, new Coord(2294, 3062));
                        put(516, new Coord(2511, 3057));
                        put(517, new Coord(3374, 2022));
                        put(518, new Coord(2747, 3052));
                        put(519, new Coord(2909, 3048));
                        put(520, new Coord(3175, 3052));
                        put(521, new Coord(3268, 2877));
                        put(522, new Coord(3268, 2632));
                        put(523, new Coord(3272, 2392));
                        put(524, new Coord(3384, 2156));
                        put(525, new Coord(3384, 2078));
                        put(526, new Coord(3369, 1869));
                        put(527, new Coord(3369, 1745));
                        put(528, new Coord(3364, 1620));
                        put(529, new Coord(3364, 1514));
                        put(530, new Coord(3364, 1374));
                        put(531, new Coord(3369, 1255));
                        put(532, new Coord(3370, 1134));
                        put(533, new Coord(3365, 1014));
                        put(534, new Coord(3476, 1818));
                        put(535, new Coord(3717, 1828));
                        put(600, new Coord(820, 2117));
                        put(601, new Coord(731, 2117));
                        put(602, new Coord(731, 2033));
                        put(603, new Coord(820, 2033));
                        put(604, new Coord(2061, 3191));
                        put(605, new Coord(2061, 3289));
                        put(606, new Coord(2061, 3345));
                        put(607, new Coord(2295, 3191));
                        put(608, new Coord(2295, 3289));
                        put(609, new Coord(2295, 3345));
                        put(610, new Coord(2187, 3359));
                        put(611, new Coord(3542, 2195));
                        put(612, new Coord(3626, 2195));
                        put(613, new Coord(3542, 2115));
                        put(614, new Coord(3626, 2115));
                        put(615, new Coord(3437, 817));
                        put(616, new Coord(3522, 817));
                        put(617, new Coord(3606, 817));

                    }}
            }
    ),
    GONG1(2, "공학 1관", new ArrayList<>(), 0, null),
    GONG3(3, "공학 3관", new ArrayList<>(), 0, null),
    GONG4(4, "공학 4관", new ArrayList<>(), 0, null),
    DAMHEON(5, "담헌실학관", new ArrayList<>(), 0, null),
    GEC(6, "국제교육센터(GEC)", new ArrayList<>(), 0, null),
    BONBU(7, "대학본부(본관)", new ArrayList<>(), 3, null),
    INMUN(8, "인문경영관", new ArrayList<>(), 5, null),
    DADAM(9, "다담 미래학습관", new ArrayList<>(), 0, null),
    DASAN(10, "다산정보관", new ArrayList<>(), 4, null),
    HAKSAENG(11, "학생회관", new ArrayList<>(), 3, null),
    TONGHAB(12, "학생통합지원센터", new ArrayList<>(), 0, null),
    BOKJI(13, "복지관", new ArrayList<>(), 3, null),
    JUNGDANG(14, "중앙공원", new ArrayList<>(), 0, null),
    SAEROM(16, "새롬관 (교육원실습동)", new ArrayList<>(), 0, null),
    SANHAK(17, "산학협력관", new ArrayList<>(), 0, null),
    GANGDANG(18, "강당", new ArrayList<>(), 0, null),
    UN_DONGJANG(19, "운동장", new ArrayList<>(), 0, null),
    CHEYUK(20, "체육관", new ArrayList<>(), 0, null),
    RND_DOM(21, "R&D돔(나래돔)", new ArrayList<>(), 0, null),
    CAMPUS_CO(22, "캠퍼스 컴퍼니", new ArrayList<>(), 0, null),
    SMART(23, "스마트러닝팩토리", new ArrayList<>(), 0, null),
    CHANGUP(24, "창업보육관", new ArrayList<>(), 0, null),
    TENNIS(25, "테니스장 부대시설", new ArrayList<>(), 0, null),
    NAOURI(26, "나우리인성관", new ArrayList<>(), 0, null),
    EORYEONJIB(27, "공동직장어린이집", new ArrayList<>(), 0, null),
    HAEOUL(28, "101동(해울관)", new ArrayList<>(), 0, null),
    YEJI(29, "102동(예지관)", new ArrayList<>(), 0, null),
    YESOL(30, "103동(예솔관)", new ArrayList<>(), 0, null),
    DASOL(31, "104동(다솔관)", new ArrayList<>(), 0, null),
    HAMJI(32, "105동(함지관)", new ArrayList<>(), 0, null),
    HANUL(33, "106동(한울관)", new ArrayList<>(), 0, null),
    SOLBIT(34, "201동(솔빛관)", new ArrayList<>(), 0, null),
    CHEONGSOL(35, "202동(청솔관)", new ArrayList<>(), 0, null),
    IH(36, "203동(IH)", new ArrayList<>(), 0, null),
    EUNSOL(37, "204동(은솔관)", new ArrayList<>(), 0, null),
    CHAMBIT(38, "205동(참빛관)", new ArrayList<>(), 0, null);



    public final int id;
    public final String name;
    public final List<Entrance> entrances;
    public final int floorCount;
    public final Map<Integer, Coord>[] coordByNumber;
    BuildingInfo(int id, String name, List<Entrance> entrances, int floorCount,
                 Map<Integer, Coord>[] coordByNumber) {
        this.id = id;
        this.name = name;
        this.entrances = entrances;
        this.floorCount = floorCount;
        this.coordByNumber = coordByNumber;
    }

    public static BuildingInfo findBuildingInfo(int buildingId) {
        for (BuildingInfo buildingInfo : BuildingInfo.values()) {
            if (buildingInfo.id == buildingId) {
                return buildingInfo;
            }
        }
        return BuildingInfo.NONE;
    }
}



