package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.violet.dao.SysRoleDao;
import com.lemon.violet.pojo.entity.SysRole;
import com.lemon.violet.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 20:16:04
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

}

