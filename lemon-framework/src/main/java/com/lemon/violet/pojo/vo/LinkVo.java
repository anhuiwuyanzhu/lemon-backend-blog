package com.lemon.violet.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 友链(Link)表实体类
 *
 * @author makejava
 * @since 2022-09-24 20:16:03
 */
@Data
@TableName("sg_link")
public class LinkVo {

    private Long id;

    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;
}

