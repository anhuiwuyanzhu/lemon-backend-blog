package com.lemon.violet.task;

import com.lemon.violet.config.RedisCache;
import com.lemon.violet.pojo.entity.Article;
import com.lemon.violet.service.ArticleService;
import com.lemon.violet.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");

        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> {
                    Article article = new Article();
                    article.setViewCount(entry.getValue().longValue());
                    article.setId(Long.valueOf(entry.getKey()));
                    return article;
                })
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articles);

    }
}
