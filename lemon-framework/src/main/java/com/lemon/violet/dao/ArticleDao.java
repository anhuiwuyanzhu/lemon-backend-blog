package com.lemon.violet.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.violet.pojo.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 21:15:58
 */
@Mapper
public interface ArticleDao extends BaseMapper<Article> {

}

