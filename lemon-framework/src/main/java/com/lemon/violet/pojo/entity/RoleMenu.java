package com.lemon.violet.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 角色和菜单关联表(SysRoleMenu)表实体类
 *
 * @author makejava
 * @since 2022-09-24 20:16:04
 */
@Data
@TableName("sys_role_menu")
public class RoleMenu {
    //角色ID
    private Long roleId;
    //菜单ID
    private Long menuId;
}

