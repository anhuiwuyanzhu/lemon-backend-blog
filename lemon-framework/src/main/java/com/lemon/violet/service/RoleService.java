package com.lemon.violet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.violet.pojo.entity.Role;

import java.util.List;

/**
 * 角色信息表(SysRole)表服务接口
 *
 * @author makejava
 * @since 2022-09-24 20:16:04
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}

