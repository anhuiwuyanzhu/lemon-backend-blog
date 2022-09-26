package com.lemon.violet.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 用户和角色关联表(SysUserRole)表实体类
 *
 * @author makejava
 * @since 2022-09-24 20:16:05
 */
@Data
@TableName("sg_user_role")
public class SysUserRole {
    //用户ID
    private Long userId;
    //角色ID
    private Long roleId;
}

