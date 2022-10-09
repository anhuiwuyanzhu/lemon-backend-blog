package com.lemon.violet.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.violet.pojo.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和菜单关联表(SysRoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 20:16:04
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<RoleMenu> {

}

