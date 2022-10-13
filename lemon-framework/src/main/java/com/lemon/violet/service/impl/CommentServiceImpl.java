package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.violet.constant.KeyConstant;
import com.lemon.violet.dao.CommentDao;
import com.lemon.violet.dao.UserDao;
import com.lemon.violet.pojo.entity.Comment;
import com.lemon.violet.pojo.entity.User;
import com.lemon.violet.pojo.vo.CommentVo;
import com.lemon.violet.pojo.vo.PageVo;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 20:16:02
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {
    @Resource
    private CommentDao commentDao;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private UserDao userDao;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) throws JsonProcessingException {

        //查询对应文章的根评论 1:articleId，2：root_id为-1
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleId)
                .eq(Comment::getRootId, KeyConstant.ROOT_COMMENT);

        //分页查询
        Page<Comment> page = new Page<>(pageNum,pageSize);
        page = commentDao.selectPage(page, queryWrapper);
        List<Comment> records = page.getRecords();


        //数据处理 1.类型转换 2.赋值username 3.赋值toCommentUserId
        List<CommentVo> commentVos = objectMapper.readValue(objectMapper.writeValueAsString(records), new TypeReference<List<CommentVo>>() {
        });
        commentVos.stream()
                .forEach(commentVo -> {
                    User user = userDao.selectById(commentVo.getCreateBy());
                    commentVo.setUsername(user.getUserName());
                    Long toCommentUserId = commentVo.getToCommentUserId();
                    if(KeyConstant.ROOT_COMMENT.equals(toCommentUserId)){
                        String userName = userDao.selectById(toCommentUserId).getUserName();
                        commentVo.setToCommentUserName(userName);
                    }
                });

        return ResponseResult.success(new PageVo<>(commentVos,page.getTotal()));
    }
}

