package com.lemon.violet.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.violet.pojo.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和角色关联表(SysUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 20:16:05
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

}

