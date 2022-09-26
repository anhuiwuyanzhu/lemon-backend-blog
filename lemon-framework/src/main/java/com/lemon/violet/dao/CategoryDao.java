package com.lemon.violet.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.violet.pojo.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 20:16:02
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

}

