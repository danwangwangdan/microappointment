package com.showaye.microappointment.constant;

/**
 * 系统接口结果常量枚举类
 * Created by huangshiming on 2017/12/27.
 */
public enum ResultConstant {

    /**
     * 成功
     */
    SUCCESS(1, "success"),
    DATABASE_ERROR(-600, "数据库访问异常"),
    SESSION_TIMEOUT(-100, "session已过期"),
    OVER_LIMIT_NUMBER(-101, "超过活动人数限制"),
    IMAGE_FORMAT_ERROR(-102, "图片格式不支持"),
    INPUT_DATA_ERROR(-200, "数据校验错误"),
    SYSTEM_EXCEPTION(-300, "系统繁忙，请稍后重试");

    public int code;
    public String message;

    ResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
