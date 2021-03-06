package com.unistrong.baidumaplibrary.util;

/**
 * 用来计算百度地图上两个点的距离
 * 一、利用勾股定理计算，适用于两点距离很近的情况；
 * <p>
 * 二、按标准的球面大圆劣弧长度计算，适用于距离较远的情况；
 * 作者：Laughing on 2018/5/27 10:27
 * 邮箱：719240226@qq.com
 */

public class DistanceUtil {
    static double DEF_PI = 3.14159265359; // PI
    static double DEF_2PI = 6.28318530712; // 2*PI
    static double DEF_PI180 = 0.01745329252; // PI/180.0
    static double DEF_R = 6370693.5; // radius of earth

    /**
     * 第一种方式，按勾股定律求结果
     *
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return
     * @author kazeik.chen QQ:77132995  2014-4-1下午4:30:09
     * TODO kazeik@163.com
     */
    public static double GetShortDistance(double lon1, double lat1, double lon2,
                                   double lat2) {
        double ew1, ns1, ew2, ns2;
        double dx, dy, dew;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 经度差
        dew = ew1 - ew2;
        // 若跨东经和西经180 度，进行调整
        if (dew > DEF_PI)
            dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
            dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
        dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
        // 勾股定理求斜边长
        distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    /**
     * 第二种方式，按大圆劣弧求距离
     *
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return
     * @author kazeik.chen QQ:77132995  2014-4-1下午4:30:30
     * TODO kazeik@163.com
     */
    public static double GetLongDistance(double lon1, double lat1, double lon2,
                                  double lat2) {
        double ew1, ns1, ew2, ns2;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 求大圆劣弧与球心所夹的角(弧度)
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1)
                * Math.cos(ns2) * Math.cos(ew1 - ew2);
        // 调整到[-1..1]范围内，避免溢出
        if (distance > 1.0)
            distance = 1.0;
        else if (distance < -1.0)
            distance = -1.0;
        // 求大圆劣弧长度
        distance = DEF_R * Math.acos(distance);
        return distance;
    }
//    //以下是测试
//    double mLat1 = 39.90923; // point1纬度
//    double mLon1 = 116.357428; // point1经度
//    double mLat2 = 39.90923;// point2纬度
//    double mLon2 = 116.397428;// point2经度
//    double distance = GetShortDistance(mLon1, mLat1, mLon2, mLat2);
}
