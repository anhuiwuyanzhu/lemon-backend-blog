package com.lemon.violet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.entity.Category;
import com.lemon.violet.pojo.vo.ResponseResult;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-09-24 20:16:02
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取文章分类
     * @return
     */
    ResponseResult getCategoryList(Integer pid) throws JsonProcessingException;
}

