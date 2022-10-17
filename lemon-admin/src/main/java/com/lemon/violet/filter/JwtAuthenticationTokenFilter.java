package com.lemon.violet.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.violet.config.RedisCache;
import com.lemon.violet.constant.KeyConstant;
import com.lemon.violet.pojo.dto.LoginUser;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.utils.JwtUtil;
import com.lemon.violet.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisCache redisCache;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1.获取请求头中的token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            //说明该接口不需要登录 直接放行
            filterChain.doFilter(request,response);
            return ;
        }

        //2.解析token，获取userId
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //token超时,token不合法
            //响应告诉前端需要重新登录
            ResponseResult error = ResponseResult.customize(403,"请先登录",null);
            WebUtils.renderString(response,objectMapper.writeValueAsString(error));
            return ;
        }
        String userId = claims.getSubject();

        //3.从redis中获取userId对应的user信息
        LoginUser loginUser = redisCache.getCacheObject(KeyConstant.BLOG_LOGIN_KEY + userId);
        //如果获取不到用户信息
        if(ObjectUtils.isEmpty(loginUser)){
            ResponseResult error = ResponseResult.customize(403,"请重新登录",null);
            WebUtils.renderString(response,objectMapper.writeValueAsString(error));
            return ;
        }

        //4.将user放入到SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
