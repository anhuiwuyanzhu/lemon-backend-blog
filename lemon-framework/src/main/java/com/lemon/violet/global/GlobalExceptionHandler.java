package com.lemon.violet.global;

import com.lemon.violet.exception.SystemException;
import com.lemon.violet.pojo.enums.CodeEnum;
import com.lemon.violet.pojo.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //从异常对象中获取提示信息封装返回
        return ResponseResult.customize(e.getCode(),e.getMsg(),null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult methodArgs(MethodArgumentNotValidException ex){
        StringBuilder sb = new StringBuilder();

        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        allErrors.stream().forEach(error->{
            sb.append(error.getDefaultMessage())
                    .append("! ");
        });

        return ResponseResult.checkFail(sb.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        e.printStackTrace();
        //从异常对象中获取提示信息封装返回
        return ResponseResult.customize(CodeEnum.SYSTEM_ERROR.getCode(),e.getMessage(),null);
    }
}
