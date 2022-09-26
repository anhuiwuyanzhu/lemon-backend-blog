package com.lemon.violet.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.violet.pojo.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色信息表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 20:16:03
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRole> {

}

