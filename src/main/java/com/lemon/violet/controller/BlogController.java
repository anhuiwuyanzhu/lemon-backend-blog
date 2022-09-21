package com.lemon.violet.controller;

import com.lemon.violet.pojo.po.Blog;
import com.lemon.violet.service.BlogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @PostMapping("/mysqlAll")
    public List<Blog> queryMysqlAll(){
        return blogService.list();
    }
}
