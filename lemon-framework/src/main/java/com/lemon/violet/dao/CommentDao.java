package com.lemon.violet.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.violet.pojo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 20:16:02
 */
@Mapper
public interface CommentDao extends BaseMapper<Comment> {

}

