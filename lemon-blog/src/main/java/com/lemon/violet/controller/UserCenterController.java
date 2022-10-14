package com.lemon.violet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserCenterController {
    @Resource
    private UserService userService;

    @GetMapping("userInfo")
    public ResponseResult userInfo() throws JsonProcessingException {
        return userService.userInfo();
    }
}
