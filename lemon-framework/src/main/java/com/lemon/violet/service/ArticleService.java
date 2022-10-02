package com.lemon.violet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.entity.Article;
import com.lemon.violet.pojo.vo.ResponseResult;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2022-09-24 21:16:00
 */
public interface ArticleService extends IService<Article> {

    /**
     * 查询热门文章
     * @return
     */
    ResponseResult queryHotArticleList() throws JsonProcessingException;

    /**
     * 文章分页查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, Integer categoryId) throws JsonProcessingException;
}

