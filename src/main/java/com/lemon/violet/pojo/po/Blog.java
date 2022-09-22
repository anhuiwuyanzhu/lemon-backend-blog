package com.lemon.violet.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lemon.violet.annotation.Log;
import com.lemon.violet.annotation.LogField;
import lombok.Data;

import java.util.Date;

@Log
@Data
@TableName("t_blog")
public class Blog {
    @TableId
    private int id;
    @LogField("文章标题")
    private String title;
    @LogField("文章作者")
    private String author;
    private String content;
    private Date createTime;

    private Date updateTime;


}
