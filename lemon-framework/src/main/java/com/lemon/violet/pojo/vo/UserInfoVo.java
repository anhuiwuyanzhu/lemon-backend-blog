package com.lemon.violet.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {
    private Long id;

    private String nickName;
    //头像
    private String avatar;

    private String sex;

    private String email;
}
