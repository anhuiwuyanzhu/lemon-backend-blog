package com.lemon.violet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.violet.pojo.dto.LoginUser;
import com.lemon.violet.pojo.entity.User;
import com.lemon.violet.pojo.vo.AdminUserInfoVo;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.pojo.vo.UserInfoVo;
import com.lemon.violet.service.MenuService;
import com.lemon.violet.service.RoleService;
import com.lemon.violet.service.UserService;
import com.lemon.violet.utils.SecurityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;

    @Resource
    private ObjectMapper objectMapper;

    @PostMapping("/user/login")
    public ResponseResult login(@Validated @RequestBody User user) throws JsonProcessingException {
        return userService.login(user);
    }
    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return userService.logout();
    }

    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() throws JsonProcessingException {
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
//        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        List<String> roleKeyList = null;

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = objectMapper.readValue(objectMapper.writeValueAsString(user), new TypeReference<UserInfoVo>() {
        });
        //封装数据返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return ResponseResult.success(adminUserInfoVo);
    }
}
