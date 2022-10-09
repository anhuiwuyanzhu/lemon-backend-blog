package com.lemon.violet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.entity.Link;
import com.lemon.violet.pojo.vo.ResponseResult;

/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-09-24 20:16:03
 */
public interface LinkService extends IService<Link> {

    /**
     * 获取所有友链
     * @return
     */
    ResponseResult getAllLink() throws JsonProcessingException;
}

