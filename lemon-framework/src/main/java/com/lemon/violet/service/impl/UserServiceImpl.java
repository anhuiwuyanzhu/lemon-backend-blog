package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.violet.dao.SysUserDao;
import com.lemon.violet.pojo.entity.User;
import com.lemon.violet.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 20:16:04
 */
@Service("sysUserService")
public class UserServiceImpl extends ServiceImpl<SysUserDao, User> implements UserService {

}

