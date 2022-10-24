package com.lemon.violet.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.violet.pojo.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色信息表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 20:16:03
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(@Param("id") Long id);
}

