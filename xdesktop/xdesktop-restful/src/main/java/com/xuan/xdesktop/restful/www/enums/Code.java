package com.xuan.xdesktop.restful.www.enums;

/**
 * Created by xuan on 17/2/21.
 */
public enum Code {
    ERROR(0, "未知错误"),
    SUCCESS(1, "成功"),
    ERROR_PARAM(2, "参数错误"),
    ERROR_PERMIT(7, "权限验证失败");

    private int    code;
    private String message;

    Code(int code, String message) {
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

    public static Code valueOf(int code) {
        Code c = ERROR;
        for (Code e : Code.values()) {
            if (e.getCode() == code) {
                c = e;
            }
        }
        return c;
    }

}
