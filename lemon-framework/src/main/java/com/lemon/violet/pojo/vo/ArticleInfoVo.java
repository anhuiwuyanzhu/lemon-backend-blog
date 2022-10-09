package com.lemon.violet.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleInfoVo {

    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;

    private String content;

    private String categoryId;
    //所属分类id
    private String categoryName;
    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    //状态（0已发布，1草稿）
    private String status;
    //访问量
    private Long viewCount;

    private Date createTime;
}
