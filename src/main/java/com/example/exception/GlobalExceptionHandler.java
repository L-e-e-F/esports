package com.example.exception;

import com.example.common.Result;
import com.example.enums.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json串
    public Result<?> error(Exception e){
        return Result.ErrorResult(ResultCode.Unknown_Exception);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody//返回json串
    public Result<?> customError(CustomException e){
        return Result.ErrorResult(e.getMessage());
    }
}
