package com.lemon.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemon.violet.config.RedisCache;
import com.lemon.violet.constant.KeyConstant;
import com.lemon.violet.dao.UserDao;
import com.lemon.violet.pojo.dto.LoginUser;
import com.lemon.violet.pojo.entity.User;
import com.lemon.violet.pojo.rto.LoginRto;
import com.lemon.violet.pojo.rto.RegisterRto;
import com.lemon.violet.pojo.vo.BlogUserLoginVo;
import com.lemon.violet.pojo.vo.ResponseResult;
import com.lemon.violet.pojo.vo.UserInfoVo;
import com.lemon.violet.service.UserService;
import com.lemon.violet.utils.JwtUtil;
import com.lemon.violet.utils.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-09-24 20:16:04
 */
@Service("sysUserService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private AuthenticationManager manager;
    @Resource
    private RedisCache redisCache;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseResult login(LoginRto user) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = manager.authenticate(authenticationToken);
        if(ObjectUtils.isEmpty(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userId = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(String.valueOf(userId));
        redisCache.setCacheObject(KeyConstant.BLOG_LOGIN_KEY+userId,loginUser);

        UserInfoVo userInfoVo = objectMapper.readValue(objectMapper.writeValueAsString(loginUser.getUser()), new TypeReference<UserInfoVo>() {
        });
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);

        return ResponseResult.success(vo);
    }

    @Override
    public ResponseResult logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(!(principal instanceof LoginUser)){
            return ResponseResult.success(null);
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid
        Long userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject(KeyConstant.BLOG_LOGIN_KEY+userId);
        return ResponseResult.success(null);
    }

    @Override
    public ResponseResult userInfo() throws JsonProcessingException {
        Long userId = SecurityUtils.getUserId();
        User user = userDao.selectById(userId);

        UserInfoVo vo = objectMapper.readValue(objectMapper.writeValueAsString(user), new TypeReference<UserInfoVo>() {
        });
        return ResponseResult.success(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        int i = userDao.updateById(user);
        return ResponseResult.success(i);
    }

    @Override
    public ResponseResult register(RegisterRto registerRto) throws JsonProcessingException {

        //重复条件判断
        if(!ObjectUtils.isEmpty(userDao.selectList(new LambdaQueryWrapper<User>().eq(User::getUserName,registerRto.getUserName())))){
            return ResponseResult.checkFail("用户名已存在");
        }
        if(!ObjectUtils.isEmpty(userDao.selectList(new LambdaQueryWrapper<User>().eq(User::getNickName,registerRto.getNickName())))){
            return ResponseResult.checkFail("昵称已存在");
        }
        if(!ObjectUtils.isEmpty(userDao.selectList(new LambdaQueryWrapper<User>().eq(User::getEmail,registerRto.getEmail())))){
            return ResponseResult.checkFail("邮箱已存在");
        }


        //密码加密
        String encode = passwordEncoder.encode(registerRto.getPassword());
        registerRto.setPassword(encode);
        User user = objectMapper.readValue(objectMapper.writeValueAsString(registerRto), new TypeReference<User>() {
        });
        int row = userDao.insert(user);

        if(row > 0){
            return ResponseResult.success("注册成功!");
        }
        return ResponseResult.checkFail("注册失败!");
    }
}

