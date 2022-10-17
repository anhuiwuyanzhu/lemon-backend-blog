package com.lemon.violet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.violet.pojo.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(SysMenu)表服务接口
 *
 * @author makejava
 * @since 2022-09-24 20:16:03
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);
}

