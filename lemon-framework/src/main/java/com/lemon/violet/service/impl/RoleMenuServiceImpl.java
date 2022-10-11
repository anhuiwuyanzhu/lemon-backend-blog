package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.violet.dao.RoleMenuDao;
import com.lemon.violet.pojo.entity.RoleMenu;
import com.lemon.violet.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 20:16:04
 */
@Service("sysRoleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {

}

