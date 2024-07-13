package com.example.webbackend1.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AttendeeResult {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    // 把构造方法私有化
    public AttendeeResult() {}

    // 成功静态方法
    public static AttendeeResult ok() {
        AttendeeResult r = new AttendeeResult();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }

    // 失败静态方法
    public static AttendeeResult error() {
        AttendeeResult r = new AttendeeResult();
        r.setSuccess(false);
        r.setCode(201);
        r.setMessage("失败");
        return r;
    }

    public AttendeeResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public AttendeeResult message(String message){
        this.setMessage(message);
        return this;
    }

    public AttendeeResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public AttendeeResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
}

