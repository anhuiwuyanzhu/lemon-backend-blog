package com.lemon.violet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.entity.Comment;
import com.lemon.violet.pojo.vo.ResponseResult;

/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-09-24 20:16:02
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) throws JsonProcessingException;
}

