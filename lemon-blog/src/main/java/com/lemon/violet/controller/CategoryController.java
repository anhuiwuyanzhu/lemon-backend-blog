package com.lemon.violet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation(value = "获取文章分类",notes = "获取文章分类")
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(Integer pid) throws JsonProcessingException {
        return categoryService.getCategoryList(pid);
    }
}
