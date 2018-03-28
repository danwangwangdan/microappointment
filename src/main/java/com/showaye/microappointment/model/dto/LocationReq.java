package com.showaye.microappointment.model.dto;

import com.showaye.microappointment.model.base.BaseModel;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018/3/10
 */
public class LocationReq extends BaseModel {
    private static final long serialVersionUID = 161691291941012914L;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 附近点的最小纬度
     */
    private String minLat;
    private String maxLat;
    private String minLng;
    private String maxLng;

    public String getMinLat() {
        return minLat;
    }

    public void setMinLat(String minLat) {
        this.minLat = minLat;
    }

    public String getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(String maxLat) {
        this.maxLat = maxLat;
    }

    public String getMaxLng() {
        return maxLng;
    }

    public void setMaxLng(String maxLng) {
        this.maxLng = maxLng;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMinLng() {
        return minLng;
    }

    public void setMinLng(String minLng) {
        this.minLng = minLng;
    }
}
