package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.violet.constants.SystemConstants;
import com.lemon.violet.dao.LinkDao;
import com.lemon.violet.pojo.entity.Link;
import com.lemon.violet.pojo.vo.LinkVo;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 20:16:03
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkDao, Link> implements LinkService {
    @Resource
    private LinkDao linkDao;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public ResponseResult getAllLink() throws JsonProcessingException {
        //查询条件：1.审核通过
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);

        //结果封装
        List<Link> links = linkDao.selectList(queryWrapper);
        List<LinkVo> linkVos = objectMapper.readValue(objectMapper.writeValueAsString(links), new TypeReference<List<LinkVo>>() {
        });
        return ResponseResult.success(linkVos);
    }
}

