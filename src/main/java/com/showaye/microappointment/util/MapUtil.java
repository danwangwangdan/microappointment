package com.showaye.microappointment.util;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018/3/10
 */
public class MapUtil {

    /**
     * 生成以中心点为中心的四方形经纬度
     *
     * @param lat    纬度
     * @param lon    精度
     * @param radius 半径（以米为单位）
     * @return
     */
    public static double[] getAround(double lat, double lon, int radius) {

        Double latitude = lat;
        Double longitude = lon;

        Double degree = (24901 * 1609) / 360.0;
        double radiusMile = radius;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * radiusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * radiusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        return new double[]{minLat, maxLat, minLng, maxLng};
    }
}
