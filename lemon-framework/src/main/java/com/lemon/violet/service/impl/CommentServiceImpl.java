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
import com.lemon.violet.exception.SystemException;
import com.lemon.violet.pojo.entity.Comment;
import com.lemon.violet.pojo.entity.User;
import com.lemon.violet.pojo.enums.CodeEnum;
import com.lemon.violet.pojo.vo.CommentVo;
import com.lemon.violet.pojo.vo.PageVo;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.service.CommentService;
import com.lemon.violet.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public ResponseResult commentList(String type, Long articleId, Integer pageNum, Integer pageSize) throws JsonProcessingException {

        //查询对应文章的根评论 1:articleId，2：root_id为-1
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(KeyConstant.ARTICLE_COMMENT.equals(type), Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, KeyConstant.ROOT_COMMENT);
        queryWrapper.eq(Comment::getType, type);

        //分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page = commentDao.selectPage(page, queryWrapper);
        List<Comment> records = page.getRecords();


        //数据处理 1.类型转换 2.赋值username 3.赋值toCommentUserId 4.添加子评论
        if (records == null) return ResponseResult.success(new PageVo<>(null, page.getTotal()));
        List<CommentVo> commentVos = getCommentVoList(records);
        commentVos.stream().forEach(commentVo -> {
            try {
                commentVo.setChildren(getChildren(commentVo.getId()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        return ResponseResult.success(new PageVo<>(commentVos, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(CodeEnum.COMMENT_NOT_CONTENT);
        }
        commentDao.insert(comment);
        return ResponseResult.success(null);
    }


    //1.类型转换 2.赋值username 3.赋值toCommentUserId
    @NotNull
    private List<CommentVo> getCommentVoList(List<Comment> records) throws JsonProcessingException {
        List<CommentVo> commentVos;
        commentVos = objectMapper.readValue(objectMapper.writeValueAsString(records), new TypeReference<List<CommentVo>>() {
        });
        commentVos.stream()
                .forEach(commentVo -> {
                    User user = userDao.selectById(commentVo.getCreateBy());
                    commentVo.setUsername(user.getUserName());
                    Long toCommentUserId = commentVo.getToCommentUserId();
                    if (toCommentUserId != -1) {
                        String userName = userDao.selectById(toCommentUserId).getUserName();
                        commentVo.setToCommentUserName(userName);
                    }
                });
        return commentVos;
    }


    /**
     * 根据根评论的id查询所对应的子评论的集合
     *
     * @param id 根评论的id
     * @return
     */
    private List<CommentVo> getChildren(Long id) throws JsonProcessingException {
        //条件构造 1:root_id为根评论，2:按照创建时间倒叙排列
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id)
                .orderByDesc(Comment::getCreateTime);
        List<Comment> comments = commentDao.selectList(queryWrapper);

        //数据处理
        List<CommentVo> commentVoList = getCommentVoList(comments);
        return commentVoList;
    }
}

