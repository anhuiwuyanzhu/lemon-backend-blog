package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.violet.constants.SystemConstants;
import com.lemon.violet.dao.CategoryDao;
import com.lemon.violet.pojo.entity.Category;
import com.lemon.violet.pojo.vo.CategoryVo;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 20:16:02
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public ResponseResult getCategoryList(Integer pid) throws JsonProcessingException {

        //入参效验 如果没有父id 赋值-1(顶级节点)
        if(pid == null){
            pid = SystemConstants.NO_PARENT_PID;
        }

        //结果封装
        List<Category> categories = categoryDao.selectNormalList(pid);
        List<CategoryVo> categoryVos = objectMapper.readValue(objectMapper.writeValueAsString(categories), new TypeReference<List<CategoryVo>>() {
        });

        return ResponseResult.success(categoryVos);
    }
}

