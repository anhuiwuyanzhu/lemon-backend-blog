package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lemon.violet.dao.UserDao;
import com.lemon.violet.pojo.dto.LoginUser;
import com.lemon.violet.pojo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userDao.selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(user)){
            throw new RuntimeException("用户不存在");
        }

        // TODO: 2022/10/11 查询权限信息封装
        return new LoginUser(user);
    }
}
