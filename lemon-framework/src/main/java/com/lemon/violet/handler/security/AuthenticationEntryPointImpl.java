package com.lemon.violet.handler.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.violet.pojo.enums.CodeEnum;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        //响应给前端
        //BadCredentialsException
        ResponseResult result = null;
        if(authException instanceof BadCredentialsException){
            result = ResponseResult.fail(CodeEnum.LOGIN_ERROR);
        }else if(authException instanceof InsufficientAuthenticationException){
            result = ResponseResult.fail(CodeEnum.NO_OPERATOR_AUTH);
        }else{
            result = ResponseResult.fail(CodeEnum.SYSTEM_ERROR);
        }
        WebUtils.renderString(response,objectMapper.writeValueAsString(result));
    }
}
