package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.violet.dao.MenuDao;
import com.lemon.violet.pojo.entity.Menu;
import com.lemon.violet.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 20:16:03
 */
@Service("sysMenuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    @Resource
    private MenuDao menuDao;
    @Override
    public List<String> selectPermsByUserId(Long id) {
        List<String> perms = null;
        if(id == 1){
            perms =  menuDao.selectAllPerms(id);
        }else {
            perms = menuDao.selectPermsByUserId(id);
        }
        return perms;
    }
}

