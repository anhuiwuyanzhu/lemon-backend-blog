package com.lemon.violet.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.violet.pojo.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 20:16:02
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

    List<Category> selectNormalList(@Param("pid") Integer pid);
}

