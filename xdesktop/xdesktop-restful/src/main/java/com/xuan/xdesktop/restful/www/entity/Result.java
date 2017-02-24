package com.xuan.xdesktop.restful.www.entity;

import com.xuan.xdesktop.restful.www.enums.Code;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gexin on 15/3/22.
 * 结果信息
 */
public class Result implements Serializable {
    private int code;
    private long   serverTime = new Date().getTime();
    private String message    = "";
    private Object result     = new HashMap<String, String>();

    public Result() {

    }

    public Result(Code code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public Result(Code code, String message) {
        this.code = code.getCode();
        this.message = message;
    }

    public Result(Code code, Object object) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.result = object;
    }

    public static Map<String, Object> createList(List list) {
        return createJsonMap("list", list);
    }

    /**
     * 简单json输入，复杂的通过resp包下的消息
     *
     * @return
     */
    public static Map<String, Object> createJsonMap(String key, Object value) {
        return createJsonMap(new String[]{key}, new Object[]{value});
    }

    public static Map<String, Object> createJsonMap(String[] keys, Object[] values) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                values[i] = new Object();
            }
            map.put(keys[i], values[i]);
        }
        return map;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
