package com.lemon.violet.controller;

import com.lemon.violet.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Api(tags = "鉴权登录相关接口")
@RequestMapping("/sys")
public class Controller {

    @Resource
    private UserService userService;


}
