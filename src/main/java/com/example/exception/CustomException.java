package com.example.exception;

import com.example.enums.ResultCode;

public class CustomException extends RuntimeException {
    private final int code;
    private final String message;

    public CustomException(ResultCode RC) {
        this.code = RC.getCode();
        this.message = RC.getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
