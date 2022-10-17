package com.lemon.violet.constants;

public class SystemConstants {
    /**
     * 文章是是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;

    /**
     * 文章是正常分布的状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;


    /**
     * 热门文章列表分页大小
     */
    public static final int HOT_ARTICLE_PAGE_SIZE = 10;


    /**
     * 分类正常
     */
    public static final String CATEGORY_NORMAL = "0";

    /**
     * 分类被禁用
     */
    public static final String CATEGORY_DISABLE = "1";


    /**
     * 采单没有父类节点
     */
    public static final Integer NO_PARENT_PID = -1;

    /**
     * 友链审核通过
     */
    public static final Integer LINK_STATUS_NORMAL = 0;

    /**
     * 友链申请未通过
     */
    public static final Integer LINK_STATUS_DISABLE = 0;

    /**
     * 友链申请中
     */
    public static final Integer LINK_STATUS_ONGOING = 0;

    /**
     * 图片上传类型
     */
    public static final String JPG = ".jpg";

    public static final String PNG = ".png";

}
