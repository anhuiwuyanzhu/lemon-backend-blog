package com.lemon.violet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.constant.KeyConstant;
import com.lemon.violet.pojo.entity.Comment;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize) throws JsonProcessingException {
        return commentService.commentList(KeyConstant.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize) throws JsonProcessingException {
        return commentService.commentList(KeyConstant.LINK_COMMENT,null,pageNum,pageSize);
    }
}
