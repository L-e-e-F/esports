package com.example.enums;

public enum ResultCode {
    SUCCESS(0, "请求成功"),
    Unknown_Exception(-1, "未知异常"),
    USER_NOT_FOUND(-1, "没有找到此用户"),
    USER_IS_FOUND(-1,"用户已经注册"),
    PASSWORD_FOUND(-1, "密码错误"),
    UN_PERMISSION(-1, "没有权限");


    final private int code;
    final private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
