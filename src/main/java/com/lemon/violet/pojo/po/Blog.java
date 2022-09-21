package com.lemon.violet.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {

    private int id;

    private String title;

    private String author;

    private String content;

    private Date createTime;

    private Date updateTime;


}
