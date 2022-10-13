package com.lemon.violet.global;

import com.lemon.violet.exception.SystemException;
import com.lemon.violet.pojo.enums.CodeEnum;
import com.lemon.violet.pojo.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //从异常对象中获取提示信息封装返回
        return ResponseResult.customize(e.getCode(),e.getMsg(),null);
    }


    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //从异常对象中获取提示信息封装返回
        return ResponseResult.customize(CodeEnum.SYSTEM_ERROR.getCode(),e.getMessage(),null);
    }
}
