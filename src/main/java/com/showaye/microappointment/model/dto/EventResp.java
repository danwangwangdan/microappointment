package com.showaye.microappointment.model.dto;

import com.showaye.microappointment.model.entity.Event;

public class EventResp extends Event {
    /**
     * 活动发布者
     */
    public static final int PUBLISHER = 0;
    /**
     * 活动参与者
     */
    public static final int ATTENDER = 1;
    /**
     * 游客
     */
    public static final int VISITOR = 2;
    private static final long serialVersionUID = -1416593388740263258L;
    /**
     * 用户类型
     */
    private int userType;


    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
