package com.lemon.violet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.entity.Article;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "文章相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/articleList")
    @ApiOperation(value = "分页查询博文列表",notes = "分页查询博文列表")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Integer categoryId) throws JsonProcessingException {
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/hotArticleList")
    @ApiOperation(value = "查询热门文章列表",notes = "查询热门文章列表")
    public ResponseResult hotArticleList() throws JsonProcessingException {
        ResponseResult ret =  articleService.queryHotArticleList();
        return ret;
    }

    @PutMapping("/updateViewCount/{id}")
    @ApiOperation(value = "增加阅读数",notes = "增加阅读数")
    public ResponseResult updateViewCount(@PathVariable(name = "id") String id){
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询文章详情",notes = "查询文章详情")
    public ResponseResult queryArticleInfo(@PathVariable(name = "id") String id) throws JsonProcessingException {
        return articleService.articleInfo(id);
    }

}
