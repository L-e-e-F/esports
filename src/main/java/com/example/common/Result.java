package com.example.common;

import com.example.enums.ResultCode;
import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    private Result(){

    }

    private Result(int code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static  Result<?> SuccessResult() {
        Result<?> result = new Result<>();
        result.code = ResultCode.SUCCESS.getCode();
        result.message = ResultCode.SUCCESS.getMessage();
        return result;
    }

    public static <T> Result<T> SuccessResult(T data) {
        Result<T> result = new Result<>();
        result.code = ResultCode.SUCCESS.getCode();
        result.message = ResultCode.SUCCESS.getMessage();
        result.data = data;
        return result;
    }

    public static Result<?> ErrorResult(){
        Result<?> result = new Result<>();
        result.code = ResultCode.Unknown_Exception.getCode();
        result.message = ResultCode.Unknown_Exception.getMessage();
        return result;
    }

    public static Result<?> ErrorResult(ResultCode RC){
        Result<?> result = new Result<>();
        result.code = RC.getCode();
        result.message = RC.getMessage();
        return result;
    }

    public static Result<?> ErrorResult(String message){
        Result<?> result = new Result<>();
        result.code = ResultCode.Unknown_Exception.getCode();
        result.message = message;
        return result;
    }
}