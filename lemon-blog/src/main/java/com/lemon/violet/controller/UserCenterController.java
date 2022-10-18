package com.lemon.violet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.entity.User;
import com.lemon.violet.pojo.rto.RegisterRto;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterRto registerRto) throws JsonProcessingException {
        return userService.register(registerRto);
    }
}
