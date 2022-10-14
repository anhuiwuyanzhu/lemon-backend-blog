package com.lemon.violet.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lemon.violet.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try{
            userId = SecurityUtils.getUserId();
        }catch (Exception e){
            e.printStackTrace();
            userId = -1L; //表示创建人是自己
        }
        Date now = Calendar.getInstance().getTime();
        this.setFieldValByName("createBy",userId,metaObject);
        this.setFieldValByName("updateBy",userId,metaObject);
        this.setFieldValByName("createTime",now,metaObject);
        this.setFieldValByName("updateTime",now,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = null;
        try{
            userId = SecurityUtils.getUserId();
        }catch (Exception e){
            e.printStackTrace();
            userId = -1L; //表示创建人是自己
        }
        Date now = Calendar.getInstance().getTime();
        this.setFieldValByName("updateTime",now,metaObject);
        this.setFieldValByName("updateBy",userId,metaObject);
    }
}
