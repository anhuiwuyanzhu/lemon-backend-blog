package com.lemon.violet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.entity.User;
import com.lemon.violet.pojo.vo.ResponseResult;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-09-24 20:16:04
 */
public interface UserService extends IService<User> {

    ResponseResult login(User user) throws JsonProcessingException;
}

