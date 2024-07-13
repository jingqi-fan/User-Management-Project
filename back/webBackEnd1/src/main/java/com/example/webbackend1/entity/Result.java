package com.example.webbackend1.entity;


public class Result {

    private static final String SUCCESS = "0";
    private static final String ERROR = "-1";

    //相应码
    private Integer code;
    //信息
    private String message;
    //返回数据
    private Object data;


    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(0);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setData(data);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(-1);
        result.setMessage(msg);
        return result;
    }
}

