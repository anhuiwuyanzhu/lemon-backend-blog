package com.lemon.violet.handler.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.violet.pojo.enums.CodeEnum;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        //响应给前端
        ResponseResult result = ResponseResult.fail(CodeEnum.NO_OPERATOR_AUTH);
        WebUtils.renderString(response,objectMapper.writeValueAsString(result));

    }
}
