package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.violet.dao.BlogMapper;
import com.lemon.violet.pojo.po.Blog;
import com.lemon.violet.pojo.vo.RespVo;
import com.lemon.violet.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog>  implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Override
    public RespVo queryMysqlPage() {
        IPage<Blog> page = new Page<>(0,1);
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Blog::getUpdateTime);
        IPage<Blog> ipage = blogMapper.selectPage(page, queryWrapper);
        List<Blog> records = ipage.getRecords();
        return RespVo.success(records);
    }
}
