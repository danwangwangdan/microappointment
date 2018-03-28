package com.showaye.microappointment.model.entity;


import com.showaye.microappointment.model.base.BaseModel;

public class User extends BaseModel {

    private static final long serialVersionUID = 9056145968791949376L;

    public static final int GENDER_MAN = 1;
    public static final int GENDER_WOMAN = 2;
    public static final int GENDER_UNKNOWN = 0;
    private Integer id;
    private String username;
    private String imageUrl;
    private Integer point;
    private String openid;
    private String city;
    private String country;
    private String province;
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
