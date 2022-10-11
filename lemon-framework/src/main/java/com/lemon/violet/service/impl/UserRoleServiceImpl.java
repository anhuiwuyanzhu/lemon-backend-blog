package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.violet.dao.UserRoleDao;
import com.lemon.violet.pojo.entity.UserRole;
import com.lemon.violet.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 20:16:05
 */
@Service("sysUserRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}

