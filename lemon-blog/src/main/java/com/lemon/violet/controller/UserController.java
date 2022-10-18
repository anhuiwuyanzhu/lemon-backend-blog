package com.lemon.violet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.entity.User;
import com.lemon.violet.pojo.rto.LoginRto;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@Validated @RequestBody LoginRto loginRto) throws JsonProcessingException {
        return userService.login(loginRto);
    }
    @PostMapping("/logout")
    public ResponseResult logout(){
        return userService.logout();
    }
}
