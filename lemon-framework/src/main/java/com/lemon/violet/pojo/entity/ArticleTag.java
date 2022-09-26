package com.lemon.violet.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 文章标签关联表(ArticleTag)表实体类
 *
 * @author makejava
 * @since 2022-09-24 20:16:00
 */
@Data
@TableName("sg_article_tag")
public class ArticleTag {
    //文章id
    private Long articleId;
    //标签id
    private Long tagId;
}

