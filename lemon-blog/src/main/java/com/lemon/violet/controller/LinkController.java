package com.lemon.violet.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.lemon.violet.pojo.entity.Link;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "友链相关链接")
@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    @ApiOperation(value = "获取所有友链",notes = "获取所有友链")
    @GetMapping("/getAllLink")
    public ResponseResult getAllLink() throws JsonProcessingException {
        return linkService.getAllLink();
    }

}
