package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.lemon.violet.constants.SystemConstants;
import com.lemon.violet.dao.ArticleDao;
import com.lemon.violet.dao.CategoryDao;
import com.lemon.violet.pojo.entity.Article;
import com.lemon.violet.pojo.entity.Category;
import com.lemon.violet.pojo.vo.*;
import com.lemon.violet.service.ArticleService;
import com.lemon.violet.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 21:16:00
 */
@Slf4j
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

    @Resource
    private ArticleDao articleDao;
    @Resource
    private CategoryDao categoryDao;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public ResponseResult queryHotArticleList() throws JsonProcessingException {

        //条件查询，1:分页，2:排序
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, SystemConstants.HOT_ARTICLE_PAGE_SIZE);
        Page<Article> articlePage = articleDao.selectPage(page, queryWrapper);


        //结果封装
        List<Article> records = articlePage.getRecords();
        List<HotArticleVo> hotArticleVos = objectMapper.readValue(objectMapper.writeValueAsString(records), new TypeReference<List<HotArticleVo>>() {
        });
        return ResponseResult.success(hotArticleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Integer categoryId) throws JsonProcessingException {

        //查询条件 1：正式发布文章，2：置顶文章优先排序，3：有分类id就查询，没有就查全部
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        Page<Article> page = new Page<>(pageNum, pageSize);
        queryWrapper
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getIsTop)
                .eq(!ObjectUtils.isEmpty(categoryId) && categoryId>0,Article::getCategoryId,categoryId);


        //结果封装,需要根据分类id查询到分类名称
        Page<Article> articlePage = articleDao.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();
        records.stream().forEach(article -> {
            Long id = article.getCategoryId();
            Category category = categoryDao.selectById(id);
            article.setCategoryName(category.getName());
        });
        List<ArticleListVo> data = objectMapper.readValue(objectMapper.writeValueAsString(records), new TypeReference<List<ArticleListVo>>() {
        });
        PageVo<List<ArticleListVo>> pageVo = new PageVo<>(data,page.getTotal());
        return ResponseResult.success(pageVo);
    }

    @Override
    public ResponseResult articleInfo(String id) throws JsonProcessingException {
        //查询条件：1.文章id
        Article article = articleDao.selectById(id);
        Category category = categoryDao.selectById(article.getCategoryId());
        if(!ObjectUtils.isEmpty(category)){
            article.setCategoryName(category.getName());
        }
        ArticleInfoVo articleInfoVo = objectMapper.readValue(objectMapper.writeValueAsString(article), new TypeReference<ArticleInfoVo>() {
        });
        return ResponseResult.success(articleInfoVo);
    }


    private <R, T> R getHotArticleVos(T source) throws JsonProcessingException {
        R target = objectMapper.readValue(objectMapper.writeValueAsString(source), new TypeReference<R>() {
        });
        return target;
    }

}

