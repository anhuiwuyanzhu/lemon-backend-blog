package com.lemon.violet.pojo.rto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterRto {
    @NotNull(message = "用户名不能为空")
    private String userName;
    //昵称
    @NotNull(message = "昵称不能为空")
    private String nickName;
    //邮箱
    @NotNull(message = "邮箱不能为空")
    private String email;
    //头像
    @NotNull(message = "头像连接不能为空")
    private String avatar;
    //密码
    @NotNull(message = "密码不能为空")
    private String password;
}
