package com.lemon.violet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.violet.pojo.po.Blog;
import com.lemon.violet.pojo.vo.RespVo;

public interface BlogService extends IService<Blog> {

    RespVo queryMysqlPage();

}
