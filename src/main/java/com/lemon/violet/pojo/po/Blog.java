package com.lemon.violet.pojo.po;

import com.lemon.violet.annotation.Log;
import com.lemon.violet.annotation.LogField;
import lombok.Data;

import java.util.Date;

@Log
@Data
public class Blog {

    private int id;
    @LogField("文章标题")
    private String title;
    @LogField("文章作者")
    private String author;

    private String content;

    private Date createTime;

    private Date updateTime;


}
