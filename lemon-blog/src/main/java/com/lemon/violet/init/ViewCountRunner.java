package com.lemon.violet.init;

import com.lemon.violet.config.RedisCache;
import com.lemon.violet.constant.KeyConstant;
import com.lemon.violet.dao.ArticleDao;
import com.lemon.violet.pojo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {

        //查询博客信息  id  viewCount
        List<Article> articles = articleDao.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> {
                    return article.getViewCount().intValue();//
                }));
        //存储到redis中
        redisCache.setCacheMap(KeyConstant.VIEW_COUNT,viewCountMap);
    }
}
