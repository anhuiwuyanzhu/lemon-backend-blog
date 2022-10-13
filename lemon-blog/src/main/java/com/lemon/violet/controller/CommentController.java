package com.lemon.violet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize) throws JsonProcessingException {
        return commentService.commentList(articleId,pageNum,pageSize);
    }
}
