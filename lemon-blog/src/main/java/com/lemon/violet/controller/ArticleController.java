package com.lemon.violet.controller;

import com.lemon.violet.pojo.vo.RespVo;
import com.lemon.violet.service.ArticleService;
import com.lemon.violet.service.ArticleTagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/test")
    public RespVo test(){
        return RespVo.success(articleService.list());
    }
}
