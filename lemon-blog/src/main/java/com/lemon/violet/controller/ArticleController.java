package com.lemon.violet.controller;

import com.lemon.violet.pojo.vo.RespVo;
import com.lemon.violet.service.ArticleService;
import com.lemon.violet.service.ArticleTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "文章相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/test")
    public RespVo test(){
        return RespVo.success(articleService.list());
    }


    @GetMapping("/articleList")
    @ApiOperation(value = "分页查询博文列表",notes = "分页查询博文列表")
    public RespVo articleList(){
        return null;
    }

    @GetMapping("/hotArticleList")
    @ApiOperation(value = "查询热门文章列表",notes = "查询热门文章列表")
    public RespVo hotArticleList(){
        return null;
    }

    @PutMapping("/updateViewCount/{id}")
    @ApiOperation(value = "增加阅读数",notes = "增加阅读数")
    public RespVo updateViewCount(@PathVariable(name = "id") String id){
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询文章详情",notes = "查询文章详情")
    public RespVo queryArticleInfo(@PathVariable(name = "id") String id){
        return null;
    }

}
